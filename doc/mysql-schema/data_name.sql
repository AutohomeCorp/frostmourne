DROP TABLE IF EXISTS data_name;
CREATE TABLE IF NOT EXISTS data_name
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    data_name       VARCHAR(200)  NOT NULL COMMENT '数据名称,不可更新',
    display_name    VARCHAR(200)  NOT NULL COMMENT '名称描述',
    data_source_id  BIGINT        NOT NULL COMMENT '所属数据源id',
    datasource_type VARCHAR(500)  NOT NULL COMMENT '数据源类型。(Elasticsearch, Influxdb)',
    timestamp_field VARCHAR(200)  COMMENT '时间字段名',
    properties      VARCHAR(2000) NOT NULL COMMENT '不同数据的附加属性',
    creator         VARCHAR(200)  NOT NULL COMMENT '创建人',
    create_at       DATETIME      NOT NULL COMMENT '创建时间',
    modifier        VARCHAR(200)  NOT NULL COMMENT '修改人',
    modify_at       DATETIME      NOT NULL COMMENT '修改时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '数据名';

CREATE UNIQUE INDEX uniq_dataname ON data_name(data_name);