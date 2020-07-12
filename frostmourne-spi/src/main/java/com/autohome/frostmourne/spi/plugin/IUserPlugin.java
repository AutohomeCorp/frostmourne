package com.autohome.frostmourne.spi.plugin;

import java.util.List;

import com.autohome.frostmourne.spi.starter.model.AccountInfo;

/**
 * 用户服务
 */
public interface IUserPlugin {

    /**
     * 获取用户信息
     *
     * @param account
     * @return
     */
    AccountInfo findByAccount(String account);

    /**
     * 用户模糊搜索
     *
     * @param keyword 模糊搜索关键词
     * @return
     */
    List<AccountInfo> search(String keyword);
}
