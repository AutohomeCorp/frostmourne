CREATE TABLE `alert_template`
(
    `id`                  bigint(20)    NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `template_name`       varchar(100)  NOT NULL COMMENT '模板名称',
    `template_type`       varchar(32)   NOT NULL COMMENT '模板类型',
    `template_union_code` varchar(200)  NOT NULL COMMENT '模板类型关联code，根据不同模板类型关联不同的源',
    `content`             varchar(5000) NOT NULL DEFAULT '' COMMENT '模板内容',
    `creator`             varchar(200)  NOT NULL COMMENT '创建人',
    `create_at`           datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modifier`            varchar(200)  NOT NULL COMMENT '修改人',
    `modify_at`           datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_template_type_template_union_code` (`template_type`, `template_union_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='报警模板';