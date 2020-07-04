package com.autohome.frostmourne.spi.plugin.defaultimpl;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles({"default"})
@IfProfileValue(name = "test-profile", value = "IntegrationTest")
class DefaultShortenLinkPluginIntegrationTest {

    @Resource
    private DefaultShortenLinkPlugin defaultShortenLinkPlugin;

    @Test
    public void shortenLinkTest() {
        String result = defaultShortenLinkPlugin.shortenLink("http://www.autohome.com.cn/abc");
    }
}