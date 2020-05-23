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