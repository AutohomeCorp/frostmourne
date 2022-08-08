package com.autohome.frostmourne.monitor.controller;

import com.autohome.frostmourne.common.contract.Protocol;
import com.autohome.frostmourne.monitor.controller.annotation.PermissionLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/heartbeat"})
public class HeartbeatController {

    @PermissionLimit(limit = false)
    @RequestMapping(value = "/")
    public Protocol<String> index() {
        return new Protocol<>("up");
    }
}
