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