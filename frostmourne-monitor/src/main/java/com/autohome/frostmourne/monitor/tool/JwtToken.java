package com.autohome.frostmourne.monitor.tool;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.autohome.frostmourne.core.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
    private final static Long EXPIRE = 1000 * 60 * 60 * 24L;

    private final static Key KEY = Keys.hmacShaKeyFor("伏地魔,狗到天荒地老,狗出新高度".getBytes(StandardCharsets.UTF_8));

    private final static String SALT = "别杀我，我是鲁班7号";

    public String generateToken(AccountInfo accountInfo) {
        return Jwts.builder().claim("salt", SALT).claim("userinfo", JacksonUtil.serialize(accountInfo))
            .setSubject(accountInfo.getAccount()).setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
            .signWith(KEY).compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().require("salt", SALT).setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    public String parseAccount(String token) {
        return parseToken(token).getSubject();
    }
}
