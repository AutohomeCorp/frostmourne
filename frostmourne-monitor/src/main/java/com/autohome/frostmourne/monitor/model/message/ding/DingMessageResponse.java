package com.autohome.frostmourne.monitor.model.message.ding;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DingMessageResponse {
    private Integer errcode;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }
}
