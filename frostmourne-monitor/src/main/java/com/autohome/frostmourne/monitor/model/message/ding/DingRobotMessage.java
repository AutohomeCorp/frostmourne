package com.autohome.frostmourne.monitor.model.message.ding;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DingRobotMessage {
    private String msgtype;

    private DingText text;

    private DingAt at;
}
