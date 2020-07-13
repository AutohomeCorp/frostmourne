package com.autohome.frostmourne.spi.contract;

public class DingRobotMessage {
    private String msgtype;

    private DingText text;

    private DingAt at;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
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
