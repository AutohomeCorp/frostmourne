
ALTER TABLE `alarm`
  ADD COLUMN `recover_notice_status` tinyint NOT NULL DEFAULT '0' COMMENT '恢复通知开关，0:关,1:开';