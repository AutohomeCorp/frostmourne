package com.autohome.frostmourne.spi.service.impl;

import javax.annotation.Resource;

import com.autohome.frostmourne.spi.plugin.IShortenLinkPlugin;
import com.autohome.frostmourne.spi.service.IShortenLinkService;
import org.springframework.stereotype.Service;

@Service
public class ShotenLinkService implements IShortenLinkService {

    @Resource
    private IShortenLinkPlugin shortenLinkPlugin;

    public String shortenLink(String longLink) {
        return shortenLinkPlugin.shortenLink(longLink);
    }
}
