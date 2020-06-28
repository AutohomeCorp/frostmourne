DROP TABLE IF EXISTS url_map;
CREATE TABLE IF NOT EXISTS url_map
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    short_key VARCHAR(50)  NOT NULL COMMENT '短key',
    short_url VARCHAR(100) NOT NULL COMMENT '短链接',
    long_url  VARCHAR(800) NOT NULL COMMENT '原链接',
    create_at DATETIME     NOT NULL COMMENT '创建时间'
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COMMENT = '短链接表';

ALTER TABLE url_map
    ADD INDEX idx_shortkey (short_key);