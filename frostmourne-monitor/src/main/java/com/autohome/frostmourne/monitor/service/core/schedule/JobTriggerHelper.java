package com.autohome.frostmourne.monitor.service.core.schedule;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.autohome.frostmourne.monitor.config.ScheduleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobTriggerHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobTriggerHelper.class);

    private ThreadPoolExecutor fastTriggerPool = null;
    private ThreadPoolExecutor slowTriggerPool = null;

    // job timeout count
    private volatile long minTim = System.currentTimeMillis() / 60000;     // ms > min
    private volatile ConcurrentMap<Long, AtomicInteger> jobTimeoutCountMap = new ConcurrentHashMap<>();

    public void start() {
        this.fastTriggerPool = new ThreadPoolExecutor(10,
                ScheduleConfig.getInstance().getTriggerPoolFastMax(),
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable runnable) {
                        return new Thread(runnable, "JobTriggerHelper-fastTriggerPool" + runnable.hashCode());
                    }
                });

        slowTriggerPool = new ThreadPoolExecutor(
                10,
                ScheduleConfig.getInstance().getTriggerPoolSlowMax(),
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(2000),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "JobTriggerHelper-slowTriggerPool-" + r.hashCode());
                    }
                });
    }

    public void stop() {
        fastTriggerPool.shutdownNow();
        slowTriggerPool.shutdownNow();
        LOGGER.info("stop trigger pool");
    }

    public void addTask(final Long alarmId) {
        // choose thread pool
        ThreadPoolExecutor triggerPool_ = fastTriggerPool;
        AtomicInteger jobTimeoutCount = jobTimeoutCountMap.get(alarmId);
        if (jobTimeoutCount!=null && jobTimeoutCount.get() > 10) {      // job-timeout 10 times in 1 min
            triggerPool_ = slowTriggerPool;
        }

        // trigger
        triggerPool_.execute(new Runnable() {
            @Override
            public void run() {

                long start = System.currentTimeMillis();

                try {
                    // do trigger
                    ScheduleConfig.getInstance().getRemoteTriggerService().trigger(alarmId);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                } finally {

                    // check timeout-count-map
                    long minTim_now = System.currentTimeMillis()/60000;
                    if (minTim != minTim_now) {
                        minTim = minTim_now;
                        jobTimeoutCountMap.clear();
                    }

                    // incr timeout-count-map
                    long cost = System.currentTimeMillis()-start;
                    if (cost > 5000) {       // ob-timeout threshold 5000ms
                        AtomicInteger timeoutCount = jobTimeoutCountMap.putIfAbsent(alarmId, new AtomicInteger(1));
                        if (timeoutCount != null) {
                            timeoutCount.incrementAndGet();
                        }
                    }
                }
            }
        });
    }

    private static JobTriggerHelper instance = new JobTriggerHelper();

    public static void toStart() {
        instance.start();
    }

    public static void toStop() {
        instance.stop();
    }

    public static void trigger(Long alarmId) {
        instance.addTask(alarmId);
    }
}
