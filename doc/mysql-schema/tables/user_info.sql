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

INSERT INTO user_info(account, full_name, team_id, mobile, email, wxid, creator, create_at, modify_at, modifier, password)
VALUES ('admin', '管理员', 1, null, 'xxx@163.com', 'wxid1', 'admin', now(), now(), 'admin', '96e79218965eb72c92a549dd5a330112');