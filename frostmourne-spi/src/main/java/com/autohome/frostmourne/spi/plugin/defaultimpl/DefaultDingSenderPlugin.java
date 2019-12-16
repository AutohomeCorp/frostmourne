package com.autohome.frostmourne.spi.plugin.defaultimpl;

import java.util.List;

import com.autohome.frostmourne.spi.plugin.IDingSenderPlugin;

public class DefaultDingSenderPlugin implements IDingSenderPlugin {

    @Override
    public boolean send(String title, String content, List<String> cellphoneList) {
        return false;
    }
}
