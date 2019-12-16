package com.autohome.frostmourne.spi.config;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.spi.starter.model.Department;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AuthConfig {

    @Bean
    public Map<String, UserInfo> userInfoMap() throws IOException {
        String json = readResource("auth/user.json");
        List<UserInfo> userInfos = JacksonUtil.deSerializeList(json, UserInfo.class);
        return userInfos.stream().collect(Collectors.toMap(UserInfo::getAccount, userInfo -> userInfo));
    }

    @Bean
    public Map<String, Team> teamMap() throws IOException {
        String json = readResource("auth/team.json");
        List<Team> teams = JacksonUtil.deSerializeList(json, Team.class);
        return teams.stream().collect(Collectors.toMap(Team::getName, team -> team));
    }

    @Bean
    public Map<String, Department> departmentMap() throws IOException {
        String json = readResource("auth/department.json");
        List<Department> departments = JacksonUtil.deSerializeList(json, Department.class);
        return departments.stream().collect(Collectors.toMap(Department::getName, department -> department));
    }

    public String readResource(String path) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try(StringWriter stringWriter = new StringWriter()) {
            IOUtils.copy(classPathResource.getInputStream(), stringWriter, StandardCharsets.UTF_8);
            String json = stringWriter.toString();
            return json;
        }
    }
}
