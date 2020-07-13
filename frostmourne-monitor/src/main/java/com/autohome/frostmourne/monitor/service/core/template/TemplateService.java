package com.autohome.frostmourne.monitor.service.core.template;

import java.io.StringWriter;
import java.util.Map;
import javax.annotation.Resource;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;

@Service
public class TemplateService implements ITemplateService {

    @Resource(name = "dynamicConfig")
    private Configuration dynamicConfig;

    @Override
    public String format(String template, Map<String, Object> env) {
        String key = md5Key(template);
        StringTemplateLoader loader = (StringTemplateLoader) dynamicConfig.getTemplateLoader();
        loader.putTemplate(key, template);

        return process(dynamicConfig, key, env);
    }

    private String process(Configuration config, String key, Map<String, Object> env) {
        try {
            Template template = config.getTemplate(key, "utf-8");
            StringWriter writer = new StringWriter();
            template.process(env, writer);
            return writer.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String md5Key(String content) {
        return Hashing.md5().newHasher().putString(content, Charsets.UTF_8).hash().toString();
    }
}
