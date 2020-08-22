package com.autohome.frostmourne.spi.config;

import com.autohome.frostmourne.spi.plugin.IDingSenderPlugin;
import com.autohome.frostmourne.spi.plugin.IShortenLinkPlugin;
import com.autohome.frostmourne.spi.plugin.ISmsSenderPlugin;
import com.autohome.frostmourne.spi.plugin.autohome.AutohomeDingSenderPlugin;
import com.autohome.frostmourne.spi.plugin.autohome.AutohomeSmsSenderPlugin;
import com.autohome.frostmourne.spi.plugin.defaultimpl.DefaultDingSenderPlugin;
import com.autohome.frostmourne.spi.plugin.defaultimpl.DefaultShortenLinkPlugin;
import com.autohome.frostmourne.spi.plugin.defaultimpl.DefaultSmsSenderPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PluginConfig {

    @Configuration
    @Profile("autohome")
    static class AutohomePluginConfig {

        @Bean
        public IDingSenderPlugin autohomeDingSenderPlugin() {
            return new AutohomeDingSenderPlugin();
        }

        @Bean
        public ISmsSenderPlugin autohomeSmsSenderPlugin() {
            return new AutohomeSmsSenderPlugin();
        }

        @Bean
        public IShortenLinkPlugin shortenLinkPlugin() {
            return new DefaultShortenLinkPlugin();
        }
    }

    @Configuration
    @Profile("default")
    static class DefaultPluginConfig {

        @Bean
        public IDingSenderPlugin dingSenderPlugin() {
            return new DefaultDingSenderPlugin();
        }

        @Bean
        public ISmsSenderPlugin smsSenderPlugin() {
            return new DefaultSmsSenderPlugin();
        }

        @Bean
        public IShortenLinkPlugin shortenLinkPlugin() {
            return new DefaultShortenLinkPlugin();
        }
    }
}
