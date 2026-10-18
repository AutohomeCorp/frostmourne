package com.autohome.frostmourne.monitor.tool;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.autohome.frostmourne.common.jackson.JacksonUtil;
import com.autohome.frostmourne.monitor.model.account.AccountInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtToken {
    private static final Long EXPIRE = 1000 * 60 * 60 * 24L;

    private final Key key;

    private static final String SALT = "别杀我，我是鲁班7号";

    public JwtToken(@Value("${frostmourne.jwt.secret:}") String secret) {
        if (!StringUtils.hasText(secret) || secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("Configure frostmourne.jwt.secret (FROSTMOURNE_JWT_SECRET) with a unique random secret of at least 32 bytes");
        }
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(AccountInfo accountInfo) {
        return Jwts.builder().claim("salt", SALT).claim("userinfo", JacksonUtil.serialize(accountInfo)).setSubject(accountInfo.getAccount())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)).signWith(key).compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().require("salt", SALT).setSigningKey(key).parseClaimsJws(token).getBody();
    }

    public String parseAccount(String token) {
        return parseToken(token).getSubject();
    }
}
