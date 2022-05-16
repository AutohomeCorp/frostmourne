DROP TABLE IF EXISTS user_role;
CREATE TABLE IF NOT EXISTS user_role
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    account   VARCHAR(200) NOT NULL COMMENT '账号',
    `role`      VARCHAR(200) NOT NULL COMMENT '角色',
    creator   VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at DATETIME     NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '用户角色关系表';

ALTER TABLE user_role
    ADD INDEX idx_account (account);