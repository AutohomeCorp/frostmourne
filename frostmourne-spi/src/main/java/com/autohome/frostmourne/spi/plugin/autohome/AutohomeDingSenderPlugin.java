package com.autohome.frostmourne.spi.plugin.autohome;

import java.util.List;

import com.autohome.frostmourne.spi.plugin.IDingSenderPlugin;

public class AutohomeDingSenderPlugin implements IDingSenderPlugin {

    @Override
    public boolean send(String title, String content, List<String> cellphoneList) {
        return true;
    }
}
