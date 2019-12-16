package com.autohome.frostmourne.monitor.service.core.template;

import java.util.Map;

public interface ITemplateService {

    String format(String template, Map<String, Object> env);
}
