DROP TABLE IF EXISTS job_lock;
CREATE TABLE IF NOT EXISTS job_lock
(
    id           INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    lock_name   VARCHAR(500)  NOT NULL COMMENT '锁名称'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '锁表';

INSERT INTO `job_lock` ( `lock_name`) VALUES ( 'schedule_lock');

ALTER TABLE `alarm` ADD COLUMN `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间';
ALTER TABLE `alarm` ADD COLUMN `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间';
