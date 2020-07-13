package com.autohome.frostmourne.spi.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.spi.starter.model.AccountInfo;
import com.autohome.frostmourne.spi.starter.model.Department;
import com.autohome.frostmourne.spi.starter.model.Team;
import com.google.common.base.Strings;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class FileUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileUserService.class);

    private String userFile;

    private String teamFile;

    private String departmentFile;

    private long lastLoadTime = 0;

    private Map<String, AccountInfo> userInfoMap;

    private Map<String, Team> teamMap;

    private Map<String, Department> departmentMap;

    public FileUserService(String userFile, String teamFile, String departmentFile) {
        this.userFile = userFile;
        this.teamFile = teamFile;
        this.departmentFile = departmentFile;
        loadFile();
        this.lastLoadTime = System.currentTimeMillis();
    }

    public AccountInfo findByAccount(String account) {
        reloadFile();
        return userInfoMap.get(account);
    }

    public List<AccountInfo> search(String keyword) {
        reloadFile();
        if (Strings.isNullOrEmpty(keyword)) {
            return new ArrayList<>(userInfoMap.values());
        }
        List<AccountInfo> accountInfos = new ArrayList<>();
        for (Map.Entry<String, AccountInfo> entry : userInfoMap.entrySet()) {
            if (entry.getKey().startsWith(keyword)) {
                accountInfos.add(entry.getValue());
            }
        }
        return accountInfos;
    }

    public List<Department> departments() {
        reloadFile();
        return new ArrayList<>(departmentMap.values());
    }

    public List<Team> teams(String department) {
        reloadFile();
        if (Strings.isNullOrEmpty(department)) {
            return new ArrayList<>(teamMap.values());
        }
        return teamMap.values().stream().
                filter(team -> team.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    private void loadFile() {
        loadUserFile();
        loadTeamFile();
        loadDepartmentFile();
    }

    private void reloadFile() {
        if (Strings.isNullOrEmpty(this.userFile) && Strings.isNullOrEmpty(this.teamFile)
                && Strings.isNullOrEmpty(this.departmentFile)) {
            return;
        }
        long current = System.currentTimeMillis();
        if (lastLoadTime > 0 && (current - lastLoadTime) < 120 * 1000) {
            return;
        }
        if (!Strings.isNullOrEmpty(this.userFile)) {
            loadUserFile();
        }
        if (!Strings.isNullOrEmpty(this.teamFile)) {
            loadTeamFile();
        }
        if (!Strings.isNullOrEmpty(this.departmentFile)) {
            loadDepartmentFile();
        }
        this.lastLoadTime = current;
    }

    private void loadUserFile() {
        String json = null;
        try {
            if (Strings.isNullOrEmpty(this.userFile)) {
                json = readResource("auth/user.json");
            } else {
                json = readFile(this.userFile, StandardCharsets.UTF_8);
            }
            List<AccountInfo> accountInfos = JacksonUtil.deSerializeList(json, AccountInfo.class);
            this.userInfoMap = accountInfos.stream().collect(Collectors.toMap(AccountInfo::getAccount, userInfo -> userInfo));
        } catch (Exception ex) {
            LOGGER.error("error when loadUserFile", ex);
            this.userInfoMap = new HashMap<>();
        }
    }

    private void loadTeamFile() {
        String json = null;
        try {
            if (Strings.isNullOrEmpty(this.teamFile)) {
                json = readResource("auth/team.json");
            } else {
                json = readFile(this.teamFile, StandardCharsets.UTF_8);
            }
            List<Team> teams = JacksonUtil.deSerializeList(json, Team.class);
            this.teamMap = teams.stream().collect(Collectors.toMap(Team::getName, team -> team));
        } catch (Exception ex) {
            LOGGER.error("error when loadTeamFile", ex);
            this.teamMap = new HashMap<>();
        }
    }

    private void loadDepartmentFile() {
        String json = null;
        try {
            if (Strings.isNullOrEmpty(this.departmentFile)) {
                json = readResource("auth/department.json");
            } else {
                json = readFile(this.departmentFile, StandardCharsets.UTF_8);
            }
            List<Department> departments = JacksonUtil.deSerializeList(json, Department.class);
            this.departmentMap = departments.stream().collect(Collectors.toMap(Department::getName, department -> department));
        } catch (Exception ex) {
            LOGGER.error("error when loadDepartmentFile", ex);
            this.departmentMap = new HashMap<>();
        }
    }

    public String readResource(String path) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(path);
        try (StringWriter stringWriter = new StringWriter()) {
            IOUtils.copy(classPathResource.getInputStream(), stringWriter, StandardCharsets.UTF_8);
            String json = stringWriter.toString();
            return json;
        }
    }

    String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
