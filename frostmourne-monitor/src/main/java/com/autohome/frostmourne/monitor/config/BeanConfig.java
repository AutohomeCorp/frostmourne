package com.autohome.frostmourne.monitor.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;

import org.elasticsearch.common.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.client.RestTemplate;

import com.autohome.frostmourne.monitor.dao.elasticsearch.ElasticsearchSourceManager;
import com.autohome.frostmourne.monitor.dao.jdbc.IDataSourceJdbcManager;
import com.autohome.frostmourne.monitor.dao.jdbc.impl.DataSourceJdbcManager;
import com.autohome.frostmourne.monitor.service.account.IAccountService;
import com.autohome.frostmourne.monitor.service.account.IAuthService;
import com.autohome.frostmourne.monitor.service.account.IUserInfoService;
import com.autohome.frostmourne.monitor.service.account.impl.DefaultAccountService;
import com.autohome.frostmourne.monitor.service.account.impl.LdapAuthService;
import com.autohome.frostmourne.monitor.service.account.impl.UserAuthService;
import com.autohome.frostmourne.monitor.tool.ClientHttpRequestFactory;

import okhttp3.OkHttpClient;


@Configuration
public class BeanConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanConfig.class);

    @Value("${initial.password}")
    private String initialPassword;

    @Value("${spring.ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.enabled}")
    private Boolean ldapEnabled;

    @Value("${spring.lap.auth.searchFilter}")
    private String searchFilter;

    @Value("${rest.proxy.enable:false}")
    private Boolean restProxyEnable;

    @Value("${rest.proxy.type:http}")
    private String restProxyType;

    @Value("${rest.proxy.hostname}")
    private String restProxyHostname;

    @Value("${rest.proxy.port}")
    private Integer restProxyPort;

    @Value("${rest.proxy.rule:}")
    private String restProxyRule;

    @Resource
    private LdapTemplate ldapTemplate;

    @Resource
    private IUserInfoService userInfoService;

    @Bean(initMethod = "init", destroyMethod = "close")
    public ElasticsearchSourceManager elasticsearchSourceManager() {
        return new ElasticsearchSourceManager();
    }

    @Resource
    private DefaultAccountService defaultAccountService;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate;
        if(restProxyEnable) {
            ClientHttpRequestFactory requestFactory = new ClientHttpRequestFactory();
            Proxy.Type type = Proxy.Type.valueOf(restProxyType.toUpperCase());
            Proxy proxy = new Proxy(type, new InetSocketAddress(restProxyHostname, restProxyPort));
            requestFactory.setProxy(proxy, restProxyRule);
            restTemplate = new RestTemplate(requestFactory);
        }else{
            restTemplate = new RestTemplate();
        }
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public IAccountService accountService(@Value("${frostmourne.account.type}") String accountType) {
        return defaultAccountService;
    }

    @Bean
    public IAuthService authService() {
        if (!Strings.isNullOrEmpty(ldapUrls) && Boolean.TRUE.equals(ldapEnabled)) {
            LOGGER.info("apply ldap auth");
            return new LdapAuthService(searchFilter, ldapTemplate, initialPassword);
        }
        LOGGER.info("apply default auth");
        return new UserAuthService(userInfoService, initialPassword);

    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public IDataSourceJdbcManager dataSourceJdbcManager() {
        return new DataSourceJdbcManager();
    }

    @Bean(name = "okHttp3Client")
    public OkHttpClient okHttp3Client() {
        return new OkHttpClient();
    }

}
