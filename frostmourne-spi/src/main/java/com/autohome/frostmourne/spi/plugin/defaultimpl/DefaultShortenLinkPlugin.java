package com.autohome.frostmourne.spi.plugin.defaultimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.spi.contract.ShortenLink45Response;
import com.autohome.frostmourne.spi.plugin.IShortenLinkPlugin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class DefaultShortenLinkPlugin implements IShortenLinkPlugin {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultShortenLinkPlugin.class);

    @Resource
    private RestTemplate restTemplate;

    @Value("${dwz45.token}")
    private String dwz45Token;

    @Value("${dwz.baidu.token}")
    private String dwzBaiduToken;

    @Value("${dwz.type}")
    private String dwzType;

    @Override
    public String shortenLink(String longLink) {
        if (Strings.isNullOrEmpty(dwzType) || dwzType.equalsIgnoreCase("dwz45")) {
            return dwz45ShortenLink(longLink);
        }

        if (dwzType.equalsIgnoreCase("baidu")) {
            return baiduShortenLink(longLink);
        }
        return null;
    }

    private String dwz45ShortenLink(String longLink) {
        if (Strings.isNullOrEmpty(dwz45Token)) {
            return null;
        }
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("url", longLink);
        postParameters.add("mark", "frostmourne");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(postParameters, headers);
        String json = restTemplate.postForObject("https://45dwz.cn/apicreate.php?token=" + dwz45Token, entity, String.class);
        ShortenLink45Response shortenLink45Response = JacksonUtil.deSerialize(json, ShortenLink45Response.class);
        return shortenLink45Response.getShortUrl();
    }

    private String baiduShortenLink(String longLink) {
        if (Strings.isNullOrEmpty(dwzBaiduToken)) {
            return null;
        }
        //https://dwz.cn/console/apidoc/v3
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("Dwz-Token", dwzBaiduToken);
        List<Map<String, String>> postData = new ArrayList<>();
        Map<String, String> url = new HashMap<>();
        url.put("LongUrl", longLink);
        url.put("TermOfValidity", "1-year");
        postData.add(url);
        ResponseEntity<String> responseEntity = null;
        try {
            HttpEntity<String> request = new HttpEntity<>(JacksonUtil.serialize(postData), headers);
            responseEntity = restTemplate.postForEntity("https://dwz.cn/api/v3/short-urls", request, String.class);
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                LOGGER.error("error when request baidu short url, response: " + JacksonUtil.serialize(responseEntity));
                return null;
            }

            Map<String, Object> map = JacksonUtil.mapper().readValue(responseEntity.getBody(), new TypeReference<Map<String, Object>>() {
            });
            int code = (int) map.get("Code");
            if (code != 0) {
                LOGGER.error("invalid baidu short url response: " + JacksonUtil.serialize(responseEntity));
                return null;
            }
            return ((Map) ((List) map.get("ShortUrls")).get(0)).get("ShortUrl").toString();
        } catch (Exception ex) {
            LOGGER.error("error when read json, response: " + (responseEntity != null ? JacksonUtil.serialize(responseEntity) : ""), ex);
            return null;
        }
    }
}
