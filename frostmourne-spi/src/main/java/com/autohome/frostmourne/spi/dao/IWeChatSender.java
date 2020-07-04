package com.autohome.frostmourne.spi.dao;

import java.util.List;

public interface IWeChatSender {

    boolean send(List<String> users, String title, String content, String wechatRobotHook);
}
