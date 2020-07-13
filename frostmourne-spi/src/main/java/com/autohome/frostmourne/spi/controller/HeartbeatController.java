package com.autohome.frostmourne.spi.controller;

import java.util.Map;

import com.autohome.frostmourne.core.contract.Protocol;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/heartbeat"})
public class HeartbeatController {

    @RequestMapping(method = RequestMethod.GET)
    public Protocol<String> index() {
        return new Protocol<>("up");
    }

    @RequestMapping(value = "/mockHttpPostUrl", method = RequestMethod.POST)
    public Protocol<String> mockHttpPostUrl(@RequestBody Map<String, Object> body) {
        return new Protocol<>("ok");
    }
}
