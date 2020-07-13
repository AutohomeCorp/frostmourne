package com.autohome.frostmourne.spi.plugin;

import java.util.List;

/**
 * 钉钉消息发送服务
 */
public interface IDingSenderPlugin {

    /**
     * 钉钉消息发送接口
     *
     * @param title      标题
     * @param content    内容
     * @param recipients 接收人号码列表
     * @return
     */
    boolean send(String title, String content, List<String> recipients);
}
