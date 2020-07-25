DROP TABLE IF EXISTS recipient_in_group;
CREATE TABLE IF NOT EXISTS recipient_in_group
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    account   VARCHAR(200) NOT NULL COMMENT '账号',
    alert_group_id BIGINT NOT NULL COMMENT '账号所属报警组Id',
    creator   VARCHAR(200) NOT NULL COMMENT '创建人',
    create_at DATETIME     NOT NULL COMMENT '创建时间',
    modify_at DATETIME     NOT NULL COMMENT '修改时间',
    modifier  VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '报警接收组和接收人关系表';