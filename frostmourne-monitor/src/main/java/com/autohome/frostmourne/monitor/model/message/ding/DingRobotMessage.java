package com.autohome.frostmourne.monitor.model.message.ding;

public class DingRobotMessage {
    private String msgtype;

    private DingMarkdown markdown;

    private DingText text;

    private DingAt at;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public DingMarkdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(DingMarkdown markdown) {
        this.markdown = markdown;
    }

    public DingText getText() {
        return text;
    }

    public void setText(DingText text) {
        this.text = text;
    }

    public DingAt getAt() {
        return at;
    }

    public void setAt(DingAt at) {
        this.at = at;
    }
}
