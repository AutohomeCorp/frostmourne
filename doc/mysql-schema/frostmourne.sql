CREATE DATABASE frostmourne DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_0900_ai_ci;

/* if your mysql not support utf8mb4_0900_ai_ci, use collate utf8mb4_general_ci instead */
-- CREATE DATABASE frostmourne DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

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
    job_id         BIGINT        NOT NULL DEFAULT 0 COMMENT '外部调度任务id',
    cron           VARCHAR(500)  NOT NULL COMMENT 'cron表达式',
    creator        VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at      DATETIME      NOT NULL COMMENT '创建时间',
    modifier       VARCHAR(200)  NOT NULL COMMENT '修改人',
    modify_at      DATETIME      NOT NULL COMMENT '修改时间',
    team_name      VARCHAR(200)  NOT NULL COMMENT '监控所属团队',
    risk_level     VARCHAR(500) COMMENT '风险等级。info: 提示；important: 重要；emergency: 紧急； crash: 我崩了',
    service_id     BIGINT(20)             DEFAULT '0' COMMENT '服务ID',
    recover_notice_status varchar(50) NOT NULL DEFAULT 'OPEN' COMMENT '恢复通知开关（OPEN,CLOSE）',
    trigger_last_time bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
    trigger_next_time bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间'
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
    alert          TINYINT     NOT NULL COMMENT '是否报警',
    verify_result  VARCHAR(50) NOT NULL DEFAULT 'NONE' COMMENT 'NONE,TRUE,FALSE',
    message        TEXT COMMENT '日志消息',
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
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id           BIGINT       NOT NULL COMMENT '监控ID',
    ways               VARCHAR(500) NOT NULL COMMENT '报警方式(sms,dingding,email,http_post,wechat)',
    silence            BIGINT       NOT NULL COMMENT '静默时间，单位：分钟',
    silence_expression VARCHAR(512) NULL COMMENT '静默判断表达式',
    creator            VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at          DATETIME     NOT NULL COMMENT '创建时间',
    allow_sms_from     INTEGER      NULL COMMENT '短信允许发送开始时间，[0,23]',
    allow_sms_to       INTEGER      NULL COMMENT '短信允许发送结束时间，[0,23]',
    ding_robot_hook    VARCHAR(500) NULL COMMENT '钉钉机器人hook地址',
    http_post_url      VARCHAR(500) COMMENT 'http post报警方式地址',
    wechat_robot_hook  VARCHAR(500) NULL COMMENT '企业微信机器人hook地址',
    feishu_robot_hook  VARCHAR(500) NULL COMMENT '飞书机器人hook地址',
    one_message_robot_hook VARCHAR(500) NULL COMMENT 'OneMessage机器人hook地址'
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
    alarm_id    BIGINT       NOT NULL COMMENT '监控ID',
    execute_id  BIGINT       NOT NULL COMMENT '监控执行ID',
    way         VARCHAR(100) NOT NULL COMMENT '报警方式',
    recipient   VARCHAR(100) NOT NULL COMMENT '报警接收人',
    content     TEXT         NOT NULL COMMENT '报警内容',
    in_silence  VARCHAR(50)  NOT NULL COMMENT '是否在静默期(YES,NO)',
    send_status VARCHAR(50)  NOT NULL COMMENT '发送状态(NONE,SUCCESS,FAIL,FORBID)',
    alert_type  VARCHAR(50)  NOT NULL COMMENT '消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)',
    create_at   DATETIME     NOT NULL COMMENT '创建时间'
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
    properties      text                  COMMENT '附加属性。json格式',
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
    query_string      text COMMENT '查询语句',
    post_data         VARCHAR(2000) COMMENT 'http数据监控，post数据内容',
    properties        VARCHAR(2000) COMMENT '附加属性JSON格式',
    creator           VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at         DATETIME     NOT NULL COMMENT '创建时间',
    bucket_type       VARCHAR(100) COMMENT '分桶类型。terms: 字段值分组; date_histogram: 时间分组',
    bucket_field      VARCHAR(100) COMMENT '分桶字段'
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
    `account`   VARCHAR(50) NOT NULL COMMENT '接收人账号不带邮箱后缀',
    type      VARCHAR(32) NOT NULL DEFAULT 'ALERT' COMMENT '归属于 ALERT:报警, ALERT_UPGRADE:报警升级',
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
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    rule_type           VARCHAR(100)  NOT NULL COMMENT '规则类型(numeric,percentage,expression)',
    alarm_id            BIGINT        NOT NULL COMMENT '报警ID',
    alert_template      VARCHAR(5000) NOT NULL COMMENT '报警内容模板',
    alert_template_type VARCHAR(32)   NOT NULL COMMENT '报警消息类型(TEXT,MARKDOWN)',
    creator             VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at           DATETIME      NOT NULL COMMENT '创建时间'
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

/*------------------------------------------- create department_info---------------------------------------------------------------------*/
DROP TABLE IF EXISTS department_info;
CREATE TABLE IF NOT EXISTS department_info
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    department_name VARCHAR(200) NOT NULL COMMENT '部门名称',
    full_name       VARCHAR(200) NOT NULL COMMENT '全称，一般是中文名字',
    creator         VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at       DATETIME     NOT NULL COMMENT '创建时间',
    modify_at       DATETIME     NOT NULL COMMENT '修改时间',
    modifier        VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '部门信息';

CREATE UNIQUE INDEX uniq_departmentname ON department_info (department_name);

/*------------------------------------------- create team_info---------------------------------------------------------------------*/
DROP TABLE IF EXISTS team_info;
CREATE TABLE IF NOT EXISTS team_info
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    team_name     VARCHAR(200) NOT NULL COMMENT '团队名称',
    full_name     VARCHAR(200) NOT NULL COMMENT '全称，一般是中文名字',
    department_id BIGINT       NOT NULL COMMENT '部门ID',
    creator       VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at     DATETIME     NOT NULL COMMENT '创建时间',
    modify_at     DATETIME     NOT NULL COMMENT '修改时间',
    modifier      VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '团队信息';

ALTER TABLE team_info
    ADD INDEX idx_teamname (team_name);

CREATE UNIQUE INDEX uniq_teamname ON team_info (team_name);

/*------------------------------------------- create user_info---------------------------------------------------------------------*/
DROP TABLE IF EXISTS user_info;
CREATE TABLE IF NOT EXISTS user_info
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    account   VARCHAR(200) NOT NULL COMMENT '账号',
    full_name VARCHAR(200) NOT NULL COMMENT '全称，一般是中文名字',
    team_id   BIGINT       NOT NULL COMMENT '所属团队id',
    mobile    VARCHAR(20) COMMENT '号码',
    email     VARCHAR(50) COMMENT '邮箱',
    wxid      VARCHAR(50) COMMENT '企业微信id',
    creator   VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at DATETIME     NOT NULL COMMENT '创建时间',
    modify_at DATETIME     NOT NULL COMMENT '修改时间',
    modifier  VARCHAR(200) NOT NULL COMMENT '最后修改人',
    password  VARCHAR(200) NOT NULL DEFAULT '' COMMENT '密码'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '用户信息';

ALTER TABLE user_info
    ADD INDEX idx_createat (create_at);

CREATE UNIQUE INDEX uniq_account ON user_info (account);

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

ALTER TABLE user_role
    ADD INDEX idx_account (account);
/*------------------------------------------- create alert_template---------------------------------------------------------------------*/
DROP TABLE IF EXISTS alert_template;
CREATE TABLE IF NOT EXISTS `alert_template`
(
    `id`                  bigint(20)    NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `template_name`       varchar(100)  NOT NULL COMMENT '模板名称',
    `template_type`       varchar(32)   NOT NULL COMMENT '模板类型',
    `template_union_code` varchar(200)  NOT NULL DEFAULT '' COMMENT '模板类型关联code，根据不同模板类型关联不同的源',
    `content`             varchar(5000) NOT NULL DEFAULT '' COMMENT '模板内容',
    `creator`             varchar(200)  NOT NULL COMMENT '创建人',
    `create_at`           datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`            varchar(200)  NOT NULL COMMENT '修改人',
    `modify_at`           datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_template_type_template_union_code` (`template_type`, `template_union_code`),
    KEY `idx_template_name` (`template_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='报警模板';
/*------------------------------------------- create service_info---------------------------------------------------------------------*/
DROP TABLE IF EXISTS service_info;
CREATE TABLE IF NOT EXISTS `service_info`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `service_name` varchar(50)  NOT NULL COMMENT '服务名称',
    `remark`       varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
    `owner`        varchar(200) NOT NULL DEFAULT '' COMMENT '负责人',
    `creator`      varchar(200) NOT NULL COMMENT '创建人',
    `create_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`     varchar(200) NOT NULL COMMENT '修改人',
    `modify_at`    datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_service_name` (`service_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='服务信息';

/*------------------------------------------- create short_link---------------------------------------------------------------------*/
DROP TABLE IF EXISTS short_link;
CREATE TABLE IF NOT EXISTS short_link
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    long_link VARCHAR(1000) NOT NULL COMMENT '监控数据类型。(http: http监控；其他值: 关联data_name表)',
    create_at DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '短链接表';

/*------------------------------------------- create config_map---------------------------------------------------------------------*/
DROP TABLE IF EXISTS config_map;
CREATE TABLE IF NOT EXISTS config_map
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    config_key   VARCHAR(500)  NOT NULL COMMENT '配置key',
    config_value VARCHAR(1000) NOT NULL COMMENT '配置value',
    creator      VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at    DATETIME      NOT NULL COMMENT '创建时间',
    modify_at    DATETIME      NOT NULL COMMENT '修改时间',
    modifier     VARCHAR(200)  NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '内部配置表';

CREATE UNIQUE INDEX uniq_configkey ON config_map (config_key);

/*------------------------------------------- create job_lock---------------------------------------------------------------------*/
DROP TABLE IF EXISTS job_lock;
CREATE TABLE IF NOT EXISTS job_lock
(
    id           INT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    lock_name   VARCHAR(500)  NOT NULL COMMENT '锁名称'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '锁表';

/*------------------------------------------- create alert_event -------------------------------------------*/
DROP TABLE IF EXISTS alert_event;
CREATE TABLE IF NOT EXISTS alert_event
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id   BIGINT      NOT NULL COMMENT '监控ID',
    alert_type VARCHAR(16) NOT NULL COMMENT '消息类型(问题报警: PROBLEM; 恢复通知: RECOVER)',
    in_silence TINYINT     NOT NULL COMMENT '是否在静默期',
    event_md5  JSON        NULL COMMENT '摘要md5',
    `upgrade`    TINYINT     NOT NULL DEFAULT 0 COMMENT '报警升级',
    create_at  DATETIME    NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
    key idx_alarm_id (alarm_id),
    key idx_create_at (create_at)
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警事件';

/*------------------------------------------- create alert_upgrade -------------------------------------------*/
DROP TABLE IF EXISTS alert_upgrade;
CREATE TABLE IF NOT EXISTS alert_upgrade
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    alarm_id          BIGINT       NOT NULL COMMENT '监控ID',
    `status`            VARCHAR(32)  NOT NULL COMMENT '状态开关 OPEN:打开  CLOSE:关闭',
    times_to_upgrade  INT          NULL COMMENT '连续报警n次后升级',
    ways              VARCHAR(512) NULL COMMENT '报警方式(sms,dingding,email,http_post,wechat)',
    ding_robot_hook   VARCHAR(512) NULL COMMENT '钉钉机器人hook地址',
    http_post_url     VARCHAR(512) NULL COMMENT 'http post报警方式地址',
    wechat_robot_hook VARCHAR(512) NULL COMMENT '企业微信机器人hook地址',
    feishu_robot_hook VARCHAR(512) NULL COMMENT '飞书机器人hook地址',
    one_message_robot_hook VARCHAR(500) NULL COMMENT 'OneMessage机器人hook地址',
    creator           VARCHAR(128) NOT NULL COMMENT '创建人',
    create_at         DATETIME     NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间'
    )
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警升级配置';

ALTER TABLE alert_upgrade
    ADD INDEX idx_alarm_id (alarm_id);
/*------------------------------------------- init data---------------------------------------------------------------------*/
INSERT INTO department_info(department_name, full_name, creator, create_at, modify_at, modifier)
VALUES ('default', '默认部门', 'admin', now(), now(), 'admin');

INSERT INTO team_info(team_name, full_name, department_id, creator, create_at, modify_at, modifier)
VALUES ('default', '炒鸡赛亚人', 1, 'admin', now(), now(), 'admin');

INSERT INTO user_info(account, full_name, team_id, mobile, email, wxid, creator, create_at, modify_at, modifier, password)
VALUES ('admin', '管理员', 1, null, 'xxx@163.com', 'wxid1', 'admin', now(), now(), 'admin', 'e10adc3949ba59abbe56e057f20f883e');

INSERT INTO user_role(account, role, creator, create_at)
VALUES ('admin', 'admin', 'admin', now());

INSERT INTO `job_lock` ( `lock_name`) VALUES ( 'schedule_lock');

INSERT INTO `alert_template`(`template_name`, `template_type`, `template_union_code`, `content`, `creator`, `create_at`, `modifier`, `modify_at`)
VALUES ('分桶统计通用','COMMON','','<#list VERIFIED_BUCKETS as bucket>\n${bucket.key}数量${bucket.value}超过阈值${THRESHOLD};\n</#list>','admin',now(),'admin',now());
