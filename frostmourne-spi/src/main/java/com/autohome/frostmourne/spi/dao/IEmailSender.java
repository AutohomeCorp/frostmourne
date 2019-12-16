package com.autohome.frostmourne.spi.dao;

import java.util.List;

public interface IEmailSender {
    boolean send(String title, String content, List<String> recipients);
}
