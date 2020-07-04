package com.autohome.frostmourne.monitor.tool;

import javax.servlet.http.HttpServletRequest;

import com.autohome.frostmourne.spi.starter.model.UserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthTool {

    public final static String USER_ATTR = "frostmourne_user";

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    public static UserInfo currentUser() {
        HttpServletRequest request = currentRequest();
        return (UserInfo) request.getAttribute(USER_ATTR);
    }
}
