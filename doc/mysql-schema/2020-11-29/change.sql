
ALTER TABLE `alarm`
  ADD COLUMN `recover_notice_status` varchar(50) NOT NULL DEFAULT 'OPEN' COMMENT '恢复通知开关（OPEN,CLOSE）';