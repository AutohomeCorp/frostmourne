package com.autohome.frostmourne.monitor.tool;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.autohome.frostmourne.monitor.model.account.AccountInfo;

public class AuthTool {

    public static final String USER_ATTR = "frostmourne_user";

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    public static AccountInfo currentUser() {
        HttpServletRequest request = currentRequest();
        return (AccountInfo)request.getAttribute(USER_ATTR);
    }

    public static String basicAuthValue(String user, String password) {
        String plainCreds = String.format("%s:%s", user, password);
        String base64Creds = Base64.getEncoder().encodeToString(plainCreds.getBytes(StandardCharsets.UTF_8));
        return "Basic " + base64Creds;
    }
}
