package com.autohome.frostmourne.monitor.schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.monitor.config.ScheduleConfig;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.Alarm;
import com.autohome.frostmourne.monitor.service.core.cron.CronExpression;

public class JobScheduleHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduleHelper.class);

    private static final JobScheduleHelper INSTANCE = new JobScheduleHelper();

    public static JobScheduleHelper getInstance() {
        return INSTANCE;
    }

    /**
     * pre read
     */
    public static final long PRE_READ_MS = 5000;
    private final long PRE_READ_COUNT = 1000;

    private volatile boolean scheduleThreadToStop = false;

    private volatile boolean ringThreadToStop = false;

    private static final Map<Integer, List<Long>> RING_DATA = new ConcurrentHashMap<>();

    private Thread scheduleThread;

    private Thread ringThread;

    public void start() {

        this.scheduleThread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5000 - System.currentTimeMillis() % 1000);
            } catch (InterruptedException e) {
                if (!scheduleThreadToStop) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
            LOGGER.info(">>>>>>>>> init scheduler success.");

            while (!scheduleThreadToStop) {
                long start = System.currentTimeMillis();

                Connection conn = null;
                Boolean connAutoCommit = null;
                PreparedStatement preparedStatement = null;
                boolean preReadSuccess = true;

                try {
                    conn = ScheduleConfig.getInstance().getDruidDataSource().getConnection();
                    connAutoCommit = conn.getAutoCommit();
                    conn.setAutoCommit(false);

                    preparedStatement =
                        conn.prepareStatement("select * from job_lock where lock_name = 'schedule_lock' for update");
                    preparedStatement.execute();

                    // tx start

                    // 1、pre read
                    long nowTime = System.currentTimeMillis();
                    // List<Alarm> scheduleList =
                    // XxlJobAdminConfig.getAdminConfig().getXxlJobInfoDao().scheduleJobQuery(nowTime + PRE_READ_MS,
                    // preReadCount);
                    List<Alarm> scheduleList = ScheduleConfig.getInstance().getAlarmRepository()
                        .querySchedule(nowTime + PRE_READ_MS, PRE_READ_COUNT);
                    if (scheduleList != null && scheduleList.size() > 0) {
                        // 2、push time-ring
                        for (Alarm alarm : scheduleList) {
                            // time-ring jump
                            if (nowTime > alarm.getTriggerNextTime() + PRE_READ_MS) {
                                // 2.1、trigger-expire > 5s：pass && make next-trigger-time
                                LOGGER.warn(">>>>>>>>>>> schedule misfire, alarmId = {}", alarm.getId());

                                // 1、misfire match
                                // FIRE_ONCE_NOW 》 trigger
                                JobTriggerHelper.trigger(alarm.getId());
                                LOGGER.debug(">>>>>>>>>>> schedule push trigger : alarmId = {}", alarm.getId());

                                // 2、fresh next
                                refreshNextValidTime(alarm, new Date());

                            } else if (nowTime > alarm.getTriggerNextTime()) {
                                // 2.2、trigger-expire < 5s：direct-trigger && make next-trigger-time

                                // 1、trigger
                                JobTriggerHelper.trigger(alarm.getId());
                                LOGGER.debug(">>>>>>>>>>> schedule push trigger : jobId = {}", alarm.getId());

                                // 2、fresh next
                                refreshNextValidTime(alarm, new Date());

                                // next-trigger-time in 5s, pre-read again
                                if (nowTime + PRE_READ_MS > alarm.getTriggerNextTime()) {

                                    // 1、make ring second
                                    int ringSecond = (int)((alarm.getTriggerNextTime() / 1000) % 60);

                                    // 2、push time ring
                                    pushTimeRing(ringSecond, alarm.getId());

                                    // 3、fresh next
                                    refreshNextValidTime(alarm, new Date(alarm.getTriggerNextTime()));

                                }

                            } else {
                                // 2.3、trigger-pre-read：time-ring trigger && make next-trigger-time

                                // 1、make ring second
                                int ringSecond = (int)((alarm.getTriggerNextTime() / 1000) % 60);

                                // 2、push time ring
                                pushTimeRing(ringSecond, alarm.getId());

                                // 3、fresh next
                                refreshNextValidTime(alarm, new Date(alarm.getTriggerNextTime()));

                            }

                        }

                        // 3、update trigger info
                        for (Alarm jobInfo : scheduleList) {
                            ScheduleConfig.getInstance().getAlarmRepository().scheduleUpdate(jobInfo.getId(),
                                jobInfo.getTriggerLastTime(), jobInfo.getTriggerNextTime());
                        }

                    } else {
                        preReadSuccess = false;
                    }

                    // tx stop
                } catch (Exception e) {
                    if (!scheduleThreadToStop) {
                        LOGGER.error(">>>>>> JobScheduleHelper#scheduleThread", e);
                    }
                } finally {
                    // commit
                    if (conn != null) {
                        try {
                            conn.commit();
                        } catch (SQLException e) {
                            if (!scheduleThreadToStop) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }
                        try {
                            conn.setAutoCommit(Boolean.TRUE.equals(connAutoCommit));
                        } catch (SQLException e) {
                            if (!scheduleThreadToStop) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            if (!scheduleThreadToStop) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }
                    }

                    // close PreparedStatement
                    if (null != preparedStatement) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException e) {
                            if (!scheduleThreadToStop) {
                                LOGGER.error(e.getMessage(), e);
                            }
                        }
                    }
                }
                long cost = System.currentTimeMillis() - start;

                // Wait seconds, align second
                if (cost < 1000) { // scan-overtime, not wait
                    try {
                        // pre-read period: success > scan each second; fail > skip this period;
                        TimeUnit.MILLISECONDS
                            .sleep((preReadSuccess ? 1000 : PRE_READ_MS) - System.currentTimeMillis() % 1000);
                    } catch (InterruptedException e) {
                        if (!scheduleThreadToStop) {
                            LOGGER.error(e.getMessage(), e);
                        }
                    }
                }
            }
        });

        scheduleThread.setDaemon(true);
        scheduleThread.setName("JobScheduleHelper#scheduleThread");
        scheduleThread.start();

        // ring thread
        ringThread = new Thread(() -> {

            while (!ringThreadToStop) {

                // align second
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 - System.currentTimeMillis() % 1000);
                } catch (InterruptedException e) {
                    if (!ringThreadToStop) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }

                try {
                    // second data
                    List<Long> ringItemData = new ArrayList<>();
                    int nowSecond = Calendar.getInstance().get(Calendar.SECOND); // 避免处理耗时太长，跨过刻度，向前校验一个刻度；
                    for (int i = 0; i < 2; i++) {
                        List<Long> tmpData = RING_DATA.remove((nowSecond + 60 - i) % 60);
                        if (tmpData != null) {
                            ringItemData.addAll(tmpData);
                        }
                    }

                    // ring trigger
                    LOGGER.debug(
                        ">>>>>>>>>>> time-ring beat : " + nowSecond + " = " + Collections.singletonList(ringItemData));
                    if (ringItemData.size() > 0) {
                        // do trigger
                        for (long jobId : ringItemData) {
                            // do trigger
                            JobTriggerHelper.trigger(jobId);
                        }
                        // clear
                        ringItemData.clear();
                    }
                } catch (Exception e) {
                    if (!ringThreadToStop) {
                        LOGGER.error(">>>>>>>>>>> JobScheduleHelper#ringThread", e);
                    }
                }
            }
            LOGGER.info(">>>>>>>>>>> JobScheduleHelper#ringThread stop");
        });
        ringThread.setDaemon(true);
        ringThread.setName("JobScheduleHelper#ringThread");
        ringThread.start();
    }

    public void toStop() {

        // 1、stop schedule
        scheduleThreadToStop = true;
        try {
            // wait
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (scheduleThread.getState() != Thread.State.TERMINATED) {
            // interrupt and wait
            scheduleThread.interrupt();
            try {
                scheduleThread.join();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        // if has ring data
        boolean hasRingData = false;
        if (!RING_DATA.isEmpty()) {
            for (int second : RING_DATA.keySet()) {
                List<Long> tmpData = RING_DATA.get(second);
                if (tmpData != null && tmpData.size() > 0) {
                    hasRingData = true;
                    break;
                }
            }
        }
        if (hasRingData) {
            try {
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        // stop ring (wait job-in-memory stop)
        ringThreadToStop = true;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (ringThread.getState() != Thread.State.TERMINATED) {
            // interrupt and wait
            ringThread.interrupt();
            try {
                ringThread.join();
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        LOGGER.info(">>>>>>>>>>> JobScheduleHelper stop");
    }

    private void refreshNextValidTime(Alarm jobInfo, Date fromTime) throws Exception {
        Date nextValidTime = generateNextValidTime(jobInfo.getCron(), fromTime);
        if (nextValidTime != null) {
            jobInfo.setTriggerLastTime(jobInfo.getTriggerNextTime());
            jobInfo.setTriggerNextTime(nextValidTime.getTime());
        } else {
            // jobInfo.setTriggerStatus(0);
            jobInfo.setTriggerLastTime(0L);
            jobInfo.setTriggerNextTime(0L);
            LOGGER.error(">>>>>>>>>>> refreshNextValidTime fail for job: jobId={}", jobInfo.getId());
        }
    }

    private void pushTimeRing(int ringSecond, long jobId) {
        // push async ring
        List<Long> ringItemData = RING_DATA.computeIfAbsent(ringSecond, k -> new ArrayList<>());
        ringItemData.add(jobId);

        LOGGER.debug(
            ">>>>>>>>>>> schedule push time-ring : " + ringSecond + " = " + Collections.singletonList(ringItemData));
    }

    public static Date generateNextValidTime(String cron, Date fromTime) throws Exception {
        return new CronExpression(cron).getNextValidTimeAfter(fromTime);
    }
}
