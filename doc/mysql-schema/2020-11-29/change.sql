
ALTER TABLE `alarm`
  ADD COLUMN `recover_notice_status` varchar(50) NOT NULL DEFAULT 'CLOSE' COMMENT '恢复通知开关（OPEN,CLOSE）';