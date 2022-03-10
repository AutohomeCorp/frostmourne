package com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository;

import java.util.Optional;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.ShortLink;

public interface IShortLinkRepository {

    Optional<ShortLink> selectByPrimaryKey(Long id);

    Long save(ShortLink shortLink);
}
