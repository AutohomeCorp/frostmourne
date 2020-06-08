package com.autohome.frostmourne.spi.dao;

import java.util.List;

public interface IWeChatSender {

    boolean send(List<String> users, String content);
}
