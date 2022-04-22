package com.autohome.frostmourne.monitor.service.core.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ShortLink;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IShortLinkRepository;
import com.autohome.frostmourne.monitor.service.core.service.IShortLinkService;

@Service
public class ShortLinkService implements IShortLinkService {

    @Resource
    private IShortLinkRepository shortLinkRepository;

    @Value("${frostmourne.monitor.address}")
    private String frostmourneMonitorAddress;

    @Override
    public String shorten(String longUrl) {
        ShortLink shortLink = new ShortLink();
        shortLink.setLongLink(longUrl);
        shortLink.setCreateAt(new Date());
        Long linkId = shortLinkRepository.save(shortLink);
        return String.format("%s/goto/%s", frostmourneMonitorAddress, linkId);
    }
}
