package com.autohome.frostmourne.spi.plugin.defaultimpl;

import java.util.List;

import com.autohome.frostmourne.spi.plugin.ISmsSenderPlugin;

public class DefaultSmsSenderPlugin implements ISmsSenderPlugin {

    @Override
    public boolean send(String title, String content, List<String> recipients) {
        return false;
    }
}
