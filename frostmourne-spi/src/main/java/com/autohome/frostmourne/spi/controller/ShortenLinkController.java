package com.autohome.frostmourne.spi.controller;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.spi.service.IShortenLinkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shorten")
public class ShortenLinkController {

    @Resource
    private IShortenLinkService shortenLinkService;

    @RequestMapping(value = "/link", method = RequestMethod.GET)
    public Protocol<String> link(@RequestParam(value = "_appId", required = true) String _appId,
                                 @RequestParam(value = "longUrl", required = true) String longUrl) {
        String shortLink = shortenLinkService.shortenLink(longUrl);
        return new Protocol<>(shortLink);
    }
}
