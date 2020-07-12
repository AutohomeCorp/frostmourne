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
    modifier  VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '用户信息';

ALTER TABLE user_info
    ADD INDEX idx_account (account);
ALTER TABLE user_info
    ADD INDEX idx_createat (create_at);

CREATE UNIQUE INDEX uniq_account ON user_info (account);

/*------------------------------------------- init data---------------------------------------------------------------------*/
INSERT INTO department_info(department_name, full_name, creator, create_at, modify_at, modifier) VALUES ('default', '默认部门', 'admin', now(), now(), 'admin');
INSERT INTO team_info(team_name, full_name, department_id, creator, create_at, modify_at, modifier) VALUES ('default', '炒鸡赛亚人', 1, 'admin', now(), now(), 'admin');
INSERT INTO user_info(account, full_name, team_id, mobile, email, wxid, creator, create_at, modify_at, modifier) VALUES ('admin', '管理员', 1, null, 'xxx@163.com', 'wxid1', 'admin', now(), now(), 'admin');