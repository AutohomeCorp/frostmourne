DROP TABLE IF EXISTS accesslog_config;
CREATE TABLE IF NOT EXISTS accesslog_config
(
  id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
  data_id     BIGINT       NOT NULL COMMENT '数据名id',
  log_at      VARCHAR(200) COMMENT 'http请求时间字段名',
  http_status VARCHAR(200) COMMENT 'http状态码字段名',
  host        VARCHAR(200) COMMENT '域名字段名',
  server_ip   VARCHAR(200) COMMENT '服务器ip字段名',
  cost        VARCHAR(200) COMMENT '耗时字段名',
  ua          VARCHAR(200) COMMENT 'user agent字段名',
  client_ip   VARCHAR(200) COMMENT '客户端ip字段名',
  uri_stem    VARCHAR(200) COMMENT 'url主干字段名',
  http_method VARCHAR(200) COMMENT 'HTTP方法字段名',
  query       VARCHAR(200) COMMENT 'query参数字段名',
  referer     VARCHAR(200) COMMENT 'referer字段名',
  creator     VARCHAR(200) NOT NULL COMMENT '创建人',
  create_at   DATETIME     NOT NULL COMMENT '创建时间',
  modify_at   DATETIME     NOT NULL COMMENT '修改时间',
  modifier    VARCHAR(200) NOT NULL COMMENT '最后修改人'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT = 'web访问日志字段映射配置表';