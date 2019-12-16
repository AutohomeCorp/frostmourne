package com.autohome.frostmourne.spi.plugin.autohome;

import java.util.List;

import com.autohome.frostmourne.spi.plugin.ISmsSenderPlugin;

public class AutohomeSmsSenderPlugin implements ISmsSenderPlugin {

    @Override
    public boolean send(String title, String content, List<String> recipients) {
        return true;
    }
}
