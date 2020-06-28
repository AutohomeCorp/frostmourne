package com.autohome.frostmourne.spi.plugin.defaultimpl;

import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.spi.contract.ShortenLink45Response;
import com.autohome.frostmourne.spi.plugin.IShortenLinkPlugin;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class DefaultShortenLinkPlugin implements IShortenLinkPlugin {

    @Resource
    private RestTemplate restTemplate;

    @Value("${dwz45.token}")
    private String dwzToken;

    @Override
    public String shortenLink(String longLink) {
        if (Strings.isNullOrEmpty(dwzToken)) {
            return null;
        }
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("url", longLink);
        postParameters.add("mark", "frostmourne");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(postParameters, headers);
        String json = restTemplate.postForObject("https://45dwz.cn/apicreate.php?token=" + dwzToken, entity, String.class);
        ShortenLink45Response shortenLink45Response = JacksonUtil.deSerialize(json, ShortenLink45Response.class);
        return shortenLink45Response.getShortUrl();
    }
}
