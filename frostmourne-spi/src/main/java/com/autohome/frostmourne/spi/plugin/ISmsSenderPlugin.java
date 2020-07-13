package com.autohome.frostmourne.spi.plugin;

import java.util.List;

/**
 * 短信发送服务
 */
public interface ISmsSenderPlugin {
    /**
     * 短信发送接口
     *
     * @param title      标题
     * @param content    短信内容
     * @param recipients 接收人号码列表
     * @return
     */
    boolean send(String title, String content, List<String> recipients);
}
