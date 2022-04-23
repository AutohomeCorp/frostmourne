package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.impl;

import java.util.Optional;
import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.ShortLink;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.mapper.dynamic.ShortLinkDynamicMapper;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IShortLinkRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ShortLinkRepository implements IShortLinkRepository {

    @Resource
    private ShortLinkDynamicMapper shortLinkDynamicMapper;

    @Override
    public Optional<ShortLink> selectByPrimaryKey(Long id) {
        return shortLinkDynamicMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long save(ShortLink shortLink) {
        shortLinkDynamicMapper.insert(shortLink);
        return shortLink.getId();
    }
}
