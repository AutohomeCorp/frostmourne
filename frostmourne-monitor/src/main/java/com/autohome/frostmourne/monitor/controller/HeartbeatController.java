package com.autohome.frostmourne.monitor.controller;

import com.autohome.frostmourne.core.contract.Protocol;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/heartbeat"})
public class HeartbeatController {

    @RequestMapping(value = "/")
    public Protocol<String> index() {
        return new Protocol<>("up");
    }
}
