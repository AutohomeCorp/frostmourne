DROP TABLE IF EXISTS alert_group;
CREATE TABLE IF NOT EXISTS alert_group
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    group_name VARCHAR(500) NOT NULL COMMENT '报警组名',
    creator   VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at DATETIME     NOT NULL COMMENT '创建时间',
    modify_at DATETIME     NOT NULL COMMENT '修改时间',
    modifier  VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警组';

ALTER TABLE recipient
    ADD INDEX idx_alertid (alert_id);
ALTER TABLE recipient
    ADD INDEX idx_alarmid (alarm_id);