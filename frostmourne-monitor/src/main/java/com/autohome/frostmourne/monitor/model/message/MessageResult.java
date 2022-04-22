package com.autohome.frostmourne.monitor.model.message;

import com.autohome.frostmourne.monitor.model.enums.MessageWay;

/**
 * 消息发送结果
 *
 * @author Aping
 * @since 2022/3/28 13:20
 */
public class MessageResult {

    private MessageWay way;

    private Boolean success;

    public MessageResult() {}

    public MessageResult(MessageWay messageWay, Boolean success) {
        this.way = messageWay;
        this.success = success;
    }

    public MessageWay getWay() {
        return way;
    }

    public void setWay(MessageWay way) {
        this.way = way;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}