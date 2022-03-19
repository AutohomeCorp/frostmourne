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
