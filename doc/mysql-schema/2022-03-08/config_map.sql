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
