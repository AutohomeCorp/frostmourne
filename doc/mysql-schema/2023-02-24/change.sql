ALTER TABLE alert ADD COLUMN one_message_robot_hook VARCHAR(500) NULL COMMENT 'OneMessage机器人hook地址';

ALTER TABLE alert_upgrade ADD COLUMN one_message_robot_hook VARCHAR(500) NULL COMMENT 'OneMessage机器人hook地址';
