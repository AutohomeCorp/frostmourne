SET MODE MySQL;

/*------------------------------------------- create alarm---------------------------------------------------------------------*/
DROP TABLE IF EXISTS alarm;
CREATE TABLE IF NOT EXISTS alarm
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    alarm_name     VARCHAR(100)  NOT NULL,
    alarm_type     VARCHAR(200)  NOT NULL,
    description    VARCHAR(1000) NOT NULL,
    owner_key      VARCHAR(200),
    status         VARCHAR(50)   NOT NULL,
    execute_result VARCHAR(50)   NOT NULL DEFAULT 'NONE',
    execute_at     DATETIME,
    job_id         BIGINT        NOT NULL DEFAULT 0,
    cron           VARCHAR(500)  NOT NULL,
    creator        VARCHAR(200)  NOT NULL,
    create_at      DATETIME      NOT NULL,
    modifier       VARCHAR(200)  NOT NULL,
    modify_at      DATETIME      NOT NULL,
    team_name      VARCHAR(200)  NOT NULL
);

CREATE INDEX idx_alarm_ownerkey ON alarm (owner_key);

/*------------------------------------------- create alarm_log -------------------------------------------*/
DROP TABLE IF EXISTS alarm_log;
CREATE TABLE IF NOT EXISTS alarm_log
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    alarm_id       BIGINT      NOT NULL,
    exe_start      DATETIME    NOT NULL,
    exe_end        DATETIME    NOT NULL,
    cost           INT         NOT NULL,
    execute_result VARCHAR(50) NOT NULL,
    verify_result  VARCHAR(50) NOT NULL DEFAULT 'NONE',
    message        TEXT,
    create_at      DATETIME    NOT NULL
);

CREATE INDEX idx_alarm_log_createat_alarmid ON alarm_log (create_at, alarm_id);

/*------------------------------------------- create alert -------------------------------------------*/
DROP TABLE IF EXISTS alert;
CREATE TABLE IF NOT EXISTS alert
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    alarm_id          BIGINT       NOT NULL,
    ways              VARCHAR(500) NOT NULL,
    silence           BIGINT       NOT NULL,
    creator           VARCHAR(200) NOT NULL,
    create_at         DATETIME     NOT NULL,
    allow_sms_from    INTEGER      NULL,
    allow_sms_to      INTEGER      NULL,
    ding_robot_hook   VARCHAR(500) NULL,
    http_post_url     VARCHAR(500),
    wechat_robot_hook VARCHAR(500) NULL
);

CREATE INDEX idx_alert_alarmid ON alert (alarm_id);

/*------------------------------------------- create alert_log -------------------------------------------*/
DROP TABLE IF EXISTS alert_log;
CREATE TABLE IF NOT EXISTS alert_log
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    alarm_id    BIGINT        NOT NULL,
    execute_id  BIGINT        NOT NULL,
    way         VARCHAR(100)  NOT NULL,
    recipient   VARCHAR(100)  NOT NULL,
    content     TEXT NOT NULL,
    in_silence  VARCHAR(50)   NOT NULL,
    send_status VARCHAR(50)   NOT NULL,
    alert_type  VARCHAR(50)   NOT NULL,
    create_at   DATETIME      NOT NULL
);

CREATE INDEX idx_alert_log_createat_recipient ON alert_log (create_at, recipient);

CREATE INDEX idx_alert_log_alarmid ON alert_log (alarm_id);

/*------------------------------------------- create data_mapping -------------------------------------------*/
DROP TABLE IF EXISTS data_mapping;
CREATE TABLE IF NOT EXISTS data_mapping
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_name         VARCHAR(200) NOT NULL,
    field_name        VARCHAR(200) NOT NULL,
    field_type        VARCHAR(200) NOT NULL,
    field_description VARCHAR(500) NOT NULL,
    creator           VARCHAR(200) NOT NULL,
    create_at         DATETIME     NOT NULL,
    modifier          VARCHAR(200) NOT NULL,
    modify_at         DATETIME     NOT NULL
);

CREATE INDEX idx_data_mapping_dataname ON data_mapping (data_name);

/*------------------------------------------- create data_name -------------------------------------------*/
DROP TABLE IF EXISTS data_name;
CREATE TABLE IF NOT EXISTS data_name
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_name       VARCHAR(200)  NOT NULL,
    display_name    VARCHAR(200)  NOT NULL,
    data_source_id  BIGINT        NOT NULL,
    datasource_type VARCHAR(500)  NOT NULL,
    timestamp_field VARCHAR(200),
    properties      VARCHAR(2000) NOT NULL,
    creator         VARCHAR(200)  NOT NULL,
    create_at       DATETIME      NOT NULL,
    modifier        VARCHAR(200)  NOT NULL,
    modify_at       DATETIME      NOT NULL
);

CREATE UNIQUE INDEX uniq_data_name_dataname ON data_name (data_name);

/*------------------------------------------- create data_source -------------------------------------------*/
DROP TABLE IF EXISTS data_source;
CREATE TABLE IF NOT EXISTS data_source
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    datasource_name VARCHAR(500) NOT NULL,
    datasource_type VARCHAR(500) NOT NULL,
    service_address VARCHAR(500) NOT NULL,
    properties      VARCHAR(2000),
    creator         VARCHAR(200) NOT NULL,
    create_at       DATETIME     NOT NULL,
    modifier        VARCHAR(200) NOT NULL,
    modify_at       DATETIME     NOT NULL
);

/*------------------------------------------- create metric -------------------------------------------*/
DROP TABLE IF EXISTS metric;
CREATE TABLE IF NOT EXISTS metric
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    aggregation_type  VARCHAR(100),
    aggregation_field VARCHAR(100),
    metric_type       VARCHAR(100) NOT NULL,
    alarm_id          BIGINT       NOT NULL,
    rule_id           BIGINT       NOT NULL,
    data_source_id    BIGINT       NOT NULL DEFAULT 0,
    data_name_id      BIGINT       NOT NULL DEFAULT 0,
    data_name         VARCHAR(200) NOT NULL,
    query_string      VARCHAR(1000),
    post_data         VARCHAR(2000),
    properties        VARCHAR(2000),
    creator           VARCHAR(200) NOT NULL,
    create_at         DATETIME     NOT NULL
);

CREATE INDEX idx_metric_ruleid ON metric (rule_id);
CREATE INDEX idx_metric_alarmid ON metric (alarm_id);

/*------------------------------------------- create recipient -------------------------------------------*/
DROP TABLE IF EXISTS recipient;
CREATE TABLE IF NOT EXISTS recipient
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    alarm_id  BIGINT      NOT NULL,
    alert_id  BIGINT      NOT NULL,
    account   VARCHAR(50) NOT NULL,
    create_at DATETIME    NOT NULL
);

CREATE INDEX idx_recipient_alertid ON recipient (alert_id);
CREATE INDEX idx_recipient_alarmid ON recipient (alarm_id);

/*------------------------------------------- create rule -------------------------------------------*/
DROP TABLE IF EXISTS rule;
CREATE TABLE IF NOT EXISTS rule
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    rule_type      VARCHAR(100)  NOT NULL,
    alarm_id       BIGINT        NOT NULL,
    alert_template VARCHAR(5000) NOT NULL,
    creator        VARCHAR(200)  NOT NULL,
    create_at      DATETIME      NOT NULL
);

CREATE INDEX idx_rule_alarmid ON rule (alarm_id);


/*------------------------------------------- create rule_property -------------------------------------------*/
DROP TABLE IF EXISTS rule_property;
CREATE TABLE IF NOT EXISTS rule_property
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    alarm_id   BIGINT        NOT NULL,
    rule_id    BIGINT        NOT NULL,
    prop_key   VARCHAR(100)  NOT NULL,
    prop_value VARCHAR(1000) NOT NULL,
    creator    VARCHAR(200)  NOT NULL,
    create_at  DATETIME      NOT NULL
);

CREATE INDEX idx_rule_property_alarmid ON rule_property (alarm_id);
CREATE INDEX idx_rule_property_ruleid ON rule_property (rule_id);

/*------------------------------------------- create department_info---------------------------------------------------------------------*/
DROP TABLE IF EXISTS department_info;
CREATE TABLE IF NOT EXISTS department_info
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(200) NOT NULL,
    full_name       VARCHAR(200) NOT NULL,
    creator         VARCHAR(200) NOT NULL,
    create_at       DATETIME     NOT NULL,
    modify_at       DATETIME     NOT NULL,
    modifier        VARCHAR(200) NOT NULL
);

CREATE UNIQUE INDEX uniq_departmentname ON department_info (department_name);

/*------------------------------------------- create team_info---------------------------------------------------------------------*/
DROP TABLE IF EXISTS team_info;
CREATE TABLE IF NOT EXISTS team_info
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    team_name     VARCHAR(200) NOT NULL,
    full_name     VARCHAR(200) NOT NULL,
    department_id BIGINT       NOT NULL,
    creator       VARCHAR(200) NOT NULL,
    create_at     DATETIME     NOT NULL,
    modify_at     DATETIME     NOT NULL,
    modifier      VARCHAR(200) NOT NULL
);

CREATE UNIQUE INDEX uniq_team_info_name ON team_info (team_name);

/*------------------------------------------- create user_info---------------------------------------------------------------------*/
DROP TABLE IF EXISTS user_info;
CREATE TABLE IF NOT EXISTS user_info
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    account   VARCHAR(200) NOT NULL,
    full_name VARCHAR(200) NOT NULL,
    team_id   BIGINT       NOT NULL,
    mobile    VARCHAR(20),
    email     VARCHAR(50),
    wxid      VARCHAR(50),
    creator   VARCHAR(200) NOT NULL,
    create_at DATETIME     NOT NULL,
    modify_at DATETIME     NOT NULL,
    modifier  VARCHAR(200) NOT NULL
);

CREATE INDEX idx_user_info_createat ON user_info (create_at);

CREATE UNIQUE INDEX uniq_user_info_account ON user_info (account);

/*------------------------------------------- create user_role---------------------------------------------------------------------*/
DROP TABLE IF EXISTS user_role;
CREATE TABLE IF NOT EXISTS user_role
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    account   VARCHAR(200) NOT NULL COMMENT '账号',
    role      VARCHAR(200) NOT NULL COMMENT '角色',
    creator   VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at DATETIME     NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '用户角色关系表';

ALTER TABLE user_role ADD INDEX idx_user_role_account (account);

/*------------------------------------------- init data---------------------------------------------------------------------*/
INSERT INTO department_info(department_name, full_name, creator, create_at, modify_at, modifier) VALUES ('default', '默认部门', 'admin', now(), now(), 'admin');
INSERT INTO team_info(team_name, full_name, department_id, creator, create_at, modify_at, modifier) VALUES ('default', '炒鸡赛亚人', 1, 'admin', now(), now(), 'admin');
INSERT INTO user_info(account, full_name, team_id, mobile, email, wxid, creator, create_at, modify_at, modifier) VALUES ('admin', '管理员', 1, null, 'xxx@163.com', 'wxid1', 'admin', now(), now(), 'admin');
INSERT INTO user_role(account, role, creator, create_at) VALUES('admin', 'admin', 'admin', now());
