CREATE DATABASE frostmourne
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_0900_ai_ci;

/* if your mysql not support utf8mb4_0900_ai_ci, use collate utf8mb4_general_ci instead
CREATE DATABASE frostmourne
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;
*/


use frostmourne;

/*------------------------------------------- create alarm---------------------------------------------------------------------*/
DROP TABLE IF EXISTS alarm;
CREATE TABLE IF NOT EXISTS alarm
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_name     VARCHAR(100)  NOT NULL COMMENT '监控名称',
    alarm_type     VARCHAR(200)  NOT NULL COMMENT '监控数据类型。(http: http监控；其他值: 关联data_name表)',
    description    VARCHAR(1000) NOT NULL COMMENT '监控描述',
    owner_key      VARCHAR(200) COMMENT '所属对象关键字',
    status         VARCHAR(50)   NOT NULL COMMENT '开关状态（OPEN,CLOSE）',
    execute_result VARCHAR(50)   NOT NULL DEFAULT 'NONE' COMMENT '最近一次执行结果',
    execute_at     DATETIME COMMENT '最近一次执行时间',
    job_id         BIGINT        NOT NULL DEFAULT 0 COMMENT '调度任务id',
    cron           VARCHAR(500)  NOT NULL COMMENT 'cron表达式',
    creator        VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at      DATETIME      NOT NULL COMMENT '创建时间',
    modifier       VARCHAR(200)  NOT NULL COMMENT '修改人',
    modify_at      DATETIME      NOT NULL COMMENT '修改时间',
    team_name      VARCHAR(200)  NOT NULL COMMENT '监控所属团队'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '监控报警';

ALTER TABLE alarm
    ADD INDEX idx_ownerkey (owner_key(20));

/*------------------------------------------- create alarm_log -------------------------------------------*/
DROP TABLE IF EXISTS alarm_log;
CREATE TABLE IF NOT EXISTS alarm_log
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id       BIGINT      NOT NULL COMMENT '监控ID',
    exe_start      DATETIME    NOT NULL COMMENT '监控任务执行开始时间',
    exe_end        DATETIME    NOT NULL COMMENT '监控任务执行结束时间',
    cost           INT         NOT NULL COMMENT '监控任务执行耗时，单位：毫秒',
    execute_result VARCHAR(50) NOT NULL COMMENT '执行结果(SUCCESS,ERROR)',
    verify_result  VARCHAR(50) NOT NULL DEFAULT 'NONE' COMMENT 'NONE,TRUE,FALSE',
    message        VARCHAR(2000) COMMENT '日志消息',
    create_at      DATETIME    NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '监控任务执行日志';

ALTER TABLE alarm_log
    ADD INDEX idx_createat_alarmid (create_at, alarm_id);

/*------------------------------------------- create alert -------------------------------------------*/
DROP TABLE IF EXISTS alert;
CREATE TABLE IF NOT EXISTS alert
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id          BIGINT       NOT NULL COMMENT '监控ID',
    ways              VARCHAR(500) NOT NULL COMMENT '报警方式(sms,dingding,email,http_post,wechat)',
    silence           BIGINT       NOT NULL COMMENT '静默时间，单位：分钟',
    creator           VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at         DATETIME     NOT NULL COMMENT '创建时间',
    allow_sms_from    INTEGER      NULL COMMENT '短信允许发送开始时间，[0,23]',
    allow_sms_to      INTEGER      NULL COMMENT '短信允许发送结束时间，[0,23]',
    ding_robot_hook   VARCHAR(500) NULL COMMENT '钉钉机器人hook地址',
    http_post_url     VARCHAR(500) COMMENT 'http post报警方式地址',
    wechat_robot_hook VARCHAR(500) NULL COMMENT '企业微信机器人hook地址'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警配置';

ALTER TABLE alert
    ADD INDEX idx_alarmid (alarm_id);

/*------------------------------------------- create alert_log -------------------------------------------*/
DROP TABLE IF EXISTS alert_log;
CREATE TABLE IF NOT EXISTS alert_log
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id    BIGINT        NOT NULL COMMENT '监控ID',
    execute_id  BIGINT        NOT NULL COMMENT '监控执行ID',
    way         VARCHAR(100)  NOT NULL COMMENT '报警方式',
    recipient   VARCHAR(100)  NOT NULL COMMENT '报警接收人',
    content     VARCHAR(2000) NOT NULL COMMENT '报警内容',
    in_silence  VARCHAR(50)   NOT NULL COMMENT '是否在静默期(YES,NO)',
    send_status VARCHAR(50)   NOT NULL COMMENT '发送状态(NONE,SUCCESS,FAIL,FORBID)',
    alert_type  VARCHAR(50)   NOT NULL COMMENT '消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)',
    create_at   DATETIME      NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警日志';

ALTER TABLE alert_log
    ADD INDEX idx_createat_recipient (create_at, recipient);

ALTER TABLE alert_log
    ADD INDEX idx_alarmid (alarm_id);

/*------------------------------------------- create data_mapping -------------------------------------------*/
DROP TABLE IF EXISTS data_mapping;
CREATE TABLE IF NOT EXISTS data_mapping
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    data_name         VARCHAR(200) NOT NULL COMMENT '数据名称',
    field_name        VARCHAR(200) NOT NULL COMMENT '字段名',
    field_type        VARCHAR(200) NOT NULL COMMENT '字段类型',
    field_description VARCHAR(500) NOT NULL COMMENT '字段说明',
    creator           VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at         DATETIME     NOT NULL COMMENT '创建时间',
    modifier          VARCHAR(200) NOT NULL COMMENT '修改人',
    modify_at         DATETIME     NOT NULL COMMENT '修改时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '数据名字段说明';

ALTER TABLE data_mapping
    ADD INDEX idx_dataname (data_name(20));

/*------------------------------------------- create data_name -------------------------------------------*/
DROP TABLE IF EXISTS data_name;
CREATE TABLE IF NOT EXISTS data_name
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    data_name       VARCHAR(200)  NOT NULL COMMENT '数据名称,不可更新',
    display_name    VARCHAR(200)  NOT NULL COMMENT '名称描述',
    data_source_id  BIGINT        NOT NULL COMMENT '所属数据源id',
    datasource_type VARCHAR(500)  NOT NULL COMMENT '数据源类型。(Elasticsearch, Influxdb)',
    timestamp_field VARCHAR(200) COMMENT '时间字段名',
    properties      VARCHAR(2000) NOT NULL COMMENT '不同数据的附加属性',
    creator         VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at       DATETIME      NOT NULL COMMENT '创建时间',
    modifier        VARCHAR(200)  NOT NULL COMMENT '修改人',
    modify_at       DATETIME      NOT NULL COMMENT '修改时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '数据名';

CREATE UNIQUE INDEX uniq_dataname ON data_name (data_name);

/*------------------------------------------- create data_source -------------------------------------------*/
DROP TABLE IF EXISTS data_source;
CREATE TABLE IF NOT EXISTS data_source
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    datasource_name VARCHAR(500) NOT NULL COMMENT '数据源名称',
    datasource_type VARCHAR(500) NOT NULL COMMENT '数据源类型。(Elasticsearch, Influxdb)',
    service_address VARCHAR(500) NOT NULL COMMENT '数据源服务地址',
    properties      VARCHAR(2000) COMMENT '附加属性。json格式',
    creator         VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at       DATETIME     NOT NULL COMMENT '创建时间',
    modifier        VARCHAR(200) NOT NULL COMMENT '修改人',
    modify_at       DATETIME     NOT NULL COMMENT '修改时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '数据源';

/*------------------------------------------- create metric -------------------------------------------*/
DROP TABLE IF EXISTS metric;
CREATE TABLE IF NOT EXISTS metric
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    aggregation_type  VARCHAR(100) COMMENT '数据源为http类型时无效。指标聚合类型. (count, spike, sum, avg)',
    aggregation_field VARCHAR(100) COMMENT '聚合字段',
    metric_type       VARCHAR(100) NOT NULL COMMENT '指标类型(numeric：数值; ring_than: 环比; same_time: 同比; object: 对象)',
    alarm_id          BIGINT       NOT NULL COMMENT '监控ID',
    rule_id           BIGINT       NOT NULL COMMENT '规则ID',
    data_source_id    BIGINT       NOT NULL DEFAULT 0 COMMENT '数据源id',
    data_name_id      BIGINT       NOT NULL DEFAULT 0 COMMENT '数据名id',
    data_name         VARCHAR(200) NOT NULL COMMENT '监控数据名。(http：表示静态http数据; 其他data_name关联data_name表)',
    query_string      VARCHAR(1000) COMMENT '查询语句',
    post_data         VARCHAR(2000) COMMENT 'http数据监控，post数据内容',
    properties        VARCHAR(2000) COMMENT '附加属性JSON格式',
    creator           VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at         DATETIME     NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '监控指标配置';

ALTER TABLE metric
    ADD INDEX idx_ruleid (rule_id);
ALTER TABLE metric
    ADD INDEX idx_alarmid (alarm_id);

/*------------------------------------------- create recipient -------------------------------------------*/
DROP TABLE IF EXISTS recipient;
CREATE TABLE IF NOT EXISTS recipient
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id  BIGINT      NOT NULL COMMENT '监控ID',
    alert_id  BIGINT      NOT NULL COMMENT '报警ID',
    account   VARCHAR(50) NOT NULL COMMENT '接收人账号不带邮箱后缀',
    create_at DATETIME    NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警接收人';

ALTER TABLE recipient
    ADD INDEX idx_alertid (alert_id);
ALTER TABLE recipient
    ADD INDEX idx_alarmid (alarm_id);

/*------------------------------------------- create rule -------------------------------------------*/
DROP TABLE IF EXISTS rule;
CREATE TABLE IF NOT EXISTS rule
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    rule_type      VARCHAR(100)  NOT NULL COMMENT '规则类型(numeric,percentage,expression)',
    alarm_id       BIGINT        NOT NULL COMMENT '报警ID',
    alert_template VARCHAR(5000) NOT NULL COMMENT '报警内容模板',
    creator        VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at      DATETIME      NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警规则配置';

ALTER TABLE rule
    ADD INDEX idx_alarmid (alarm_id);


/*------------------------------------------- create rule_property -------------------------------------------*/
DROP TABLE IF EXISTS rule_property;
CREATE TABLE IF NOT EXISTS rule_property
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id   BIGINT        NOT NULL COMMENT '监控Id',
    rule_id    BIGINT        NOT NULL COMMENT '报警规则ID',
    prop_key   VARCHAR(100)  NOT NULL COMMENT '属性key',
    prop_value VARCHAR(1000) NOT NULL COMMENT '属性value',
    creator    VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at  DATETIME      NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警规则属性';

ALTER TABLE rule_property
    ADD INDEX idx_alarmid (alarm_id);
ALTER TABLE rule_property
    ADD INDEX idx_ruleid (rule_id);

