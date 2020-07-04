package com.autohome.frostmourne.monitor.controller.interceptor;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.autohome.frostmourne.core.contract.Protocol;
import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.controller.annotation.PermissionLimit;
import com.autohome.frostmourne.monitor.tool.AuthTool;
import com.autohome.frostmourne.monitor.tool.JwtToken;
import com.autohome.frostmourne.spi.starter.model.UserInfo;
import io.jsonwebtoken.Claims;
import org.elasticsearch.common.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	/*@Resource
	private LoginService loginService;*/

    @Resource
    private JwtToken jwtToken;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return super.preHandle(request, response, handler);
        }

        boolean needLogin = true;
        HandlerMethod method = (HandlerMethod) handler;
        PermissionLimit permission = method.getMethodAnnotation(PermissionLimit.class);
        if (permission != null) {
            needLogin = permission.limit();
        }
        if (needLogin) {
            String token = request.getHeader("Frostmourne-Token");
            if (Strings.isNullOrEmpty(token)) {
                notLoginResponse(response);
                return false;
            }
            Claims claims = null;
            try {
                claims = jwtToken.parseToken(token);
            } catch (Exception ex) {
                wrongTokenResponse(response);
                return false;
            }
            String json = (String) claims.get("userinfo");
            UserInfo userInfo = JacksonUtil.deSerialize(json, UserInfo.class);
            request.setAttribute(AuthTool.USER_ATTR, userInfo);
        }

        return super.preHandle(request, response, handler);
    }

    private void notLoginResponse(HttpServletResponse response) throws IOException {
        Protocol protocol = new Protocol();
        protocol.setReturncode(50008);
        protocol.setMessage("not login request");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JacksonUtil.serialize(protocol));
    }

    private void wrongTokenResponse(HttpServletResponse response) throws IOException {
        Protocol protocol = new Protocol();
        protocol.setReturncode(50012);
        protocol.setMessage("wrong token");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JacksonUtil.serialize(protocol));
    }

}
