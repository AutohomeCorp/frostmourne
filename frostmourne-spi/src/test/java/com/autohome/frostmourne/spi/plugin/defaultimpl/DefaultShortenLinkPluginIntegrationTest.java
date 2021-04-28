package com.autohome.frostmourne.spi.plugin.defaultimpl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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

    @Test
    public void baiduResponseTest() throws JsonProcessingException {
        String json = "{" +
                "    \"Code\": -99," +
                "    \"ShortUrls\": [" +
                "        {   " +
                "            \"ShortUrl\": \"https://dwz.cn/de3rp2Fl\"," +
                "            \"LongUrl\": \"https://www.baidu.com\"" +
                "        }," +
                "        {   " +
                "            \"Code\": -11,        " +
                "            \"LongUrl\": \"https://notexsit.dwz.cn\"," +
                "            \"ErrMsg\": \"long URL with unsupported host\"" +
                "        }" +
                "    ]," +
                "    \"ErrMsg\": \"partial fail\"" +
                "}";
        Map<String, Object> map = JacksonUtil.mapper().readValue(json, new TypeReference<Map<String, Object>>() {
        });
        int code = (int) map.get("Code");
        String shortLink = ((Map) ((List) map.get("ShortUrls")).get(0)).get("ShortUrl").toString();
    }
}
