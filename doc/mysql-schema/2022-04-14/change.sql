ALTER TABLE `rule`
    ADD COLUMN alert_template_type VARCHAR(32) NOT NULL default 'TEXT' COMMENT '报警消息类型(TEXT,MARKDOWN)';
