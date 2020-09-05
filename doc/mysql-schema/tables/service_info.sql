DROP TABLE IF EXISTS service_info;
CREATE TABLE IF NOT EXISTS `service_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `service_name` varchar(50) NOT NULL COMMENT '服务名称',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `owner` varchar(200) NOT NULL DEFAULT '' COMMENT '负责人',
  `creator` varchar(200) NOT NULL COMMENT '创建人',
  `create_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(200) NOT NULL COMMENT '修改人',
  `modify_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_service_name` (`service_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务信息';

ALTER TABLE `alarm`
  ADD COLUMN `service_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '服务ID';