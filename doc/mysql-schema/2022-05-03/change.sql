ALTER TABLE `metric` ADD COLUMN bucket_type  VARCHAR(100) COMMENT '分桶类型。terms: 字段值分组; date_histogram: 时间分组';
ALTER TABLE `metric` ADD COLUMN bucket_field  VARCHAR(100) COMMENT '分桶字段';

INSERT INTO `alert_template`(`template_name`, `template_type`, `template_union_code`, `content`, `creator`, `create_at`, `modifier`, `modify_at`)
VALUES ('bucket','COMMON','','<#list VERIFIED_BUCKETS as bucket>\n${bucket.key}数量${bucket.value}超过${THRESHOLD};\n</#list>','admin',now(),'admin',now());
