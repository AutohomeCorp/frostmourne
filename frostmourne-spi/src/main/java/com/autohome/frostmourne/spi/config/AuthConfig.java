package com.autohome.frostmourne.spi.config;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.spi.starter.model.Department;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AuthConfig {

    @Value("${auth.user.jsonfile}")
    private String userJsonFile;

    @Value("${auth.team.jsonfile}")
    private String teamJsonFile;

    @Value("${auth.department.jsonfile}")
    private String departmentJsonFile;

    @Bean
    public Map<String, UserInfo> userInfoMap() throws IOException {
        String json = null;
        if(Strings.isNullOrEmpty(userJsonFile)) {
            json = readResource("auth/user.json");
        } else {
            json = readFile(userJsonFile, StandardCharsets.UTF_8);
        }

        List<UserInfo> userInfos = JacksonUtil.deSerializeList(json, UserInfo.class);
        return userInfos.stream().collect(Collectors.toMap(UserInfo::getAccount, userInfo -> userInfo));
    }

    @Bean
    public Map<String, Team> teamMap() throws IOException {
        String json = null;
        if(Strings.isNullOrEmpty(teamJsonFile)) {
            json = readResource("auth/team.json");
        } else {
            json = readFile(teamJsonFile, StandardCharsets.UTF_8);
        }
        List<Team> teams = JacksonUtil.deSerializeList(json, Team.class);
        return teams.stream().collect(Collectors.toMap(Team::getName, team -> team));
    }

    @Bean
    public Map<String, Department> departmentMap() throws IOException {
        String json = null;
        if(Strings.isNullOrEmpty(departmentJsonFile)) {
            json = readResource("auth/department.json");
        } else {
            json = readFile(departmentJsonFile, StandardCharsets.UTF_8);
        }
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

    String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
