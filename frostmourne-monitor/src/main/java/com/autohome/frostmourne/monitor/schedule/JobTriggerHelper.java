package com.autohome.frostmourne.monitor.schedule;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.config.ScheduleConfig;

public class JobTriggerHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobTriggerHelper.class);

    private static final JobTriggerHelper INSTANCE = new JobTriggerHelper();

    private ThreadPoolExecutor fastTriggerPool = null;
    private ThreadPoolExecutor slowTriggerPool = null;

    /**
     * job timeout count, ms > min
     */
    private volatile long minTim = System.currentTimeMillis() / 60000;
    private volatile ConcurrentMap<Long, AtomicInteger> jobTimeoutCountMap = new ConcurrentHashMap<>();

    public void start() {
        this.fastTriggerPool = new ThreadPoolExecutor(10, ScheduleConfig.getInstance().getTriggerPoolFastMax(), 60L,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000),
            runnable -> new Thread(runnable, "JobTriggerHelper-fastTriggerPool" + runnable.hashCode()));

        slowTriggerPool = new ThreadPoolExecutor(10, ScheduleConfig.getInstance().getTriggerPoolSlowMax(), 60L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(2000),
            r -> new Thread(r, "JobTriggerHelper-slowTriggerPool-" + r.hashCode()));
    }

    public void stop() {
        fastTriggerPool.shutdownNow();
        slowTriggerPool.shutdownNow();
        LOGGER.info("stop trigger pool");
    }

    public void addTask(final Long alarmId) {
        // choose thread pool
        ThreadPoolExecutor triggerPool = fastTriggerPool;
        AtomicInteger jobTimeoutCount = jobTimeoutCountMap.get(alarmId);
        if (jobTimeoutCount != null && jobTimeoutCount.get() > 10) { // job-timeout 10 times in 1 min
            triggerPool = slowTriggerPool;
        }

        // trigger
        triggerPool.execute(() -> {

            long start = System.currentTimeMillis();

            try {
                // do trigger
                ScheduleConfig.getInstance().getRemoteTriggerService().trigger(alarmId);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            } finally {

                // check timeout-count-map
                long minTimNow = System.currentTimeMillis() / 60000;
                if (minTim != minTimNow) {
                    minTim = minTimNow;
                    jobTimeoutCountMap.clear();
                }

                // incr timeout-count-map
                long cost = System.currentTimeMillis() - start;
                if (cost > 5000) { // ob-timeout threshold 5000ms
                    AtomicInteger timeoutCount = jobTimeoutCountMap.putIfAbsent(alarmId, new AtomicInteger(1));
                    if (timeoutCount != null) {
                        timeoutCount.incrementAndGet();
                    }
                }
            }
        });
    }



    public static void toStart() {
        INSTANCE.start();
    }

    public static void toStop() {
        INSTANCE.stop();
    }

    public static void trigger(Long alarmId) {
        INSTANCE.addTask(alarmId);
    }
}
