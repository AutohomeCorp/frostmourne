package com.autohome.frostmourne.monitor.model.message.ding;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DingAt {

    private Boolean isAtAll;

    private List<String> atMobiles;

}
