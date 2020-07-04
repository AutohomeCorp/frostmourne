package com.autohome.frostmourne.monitor.tool;

import java.security.Key;
import java.util.Date;

import com.autohome.frostmourne.spi.starter.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class JwtTokenTest {

    @Rule
    public ExpectedException excpectedException = ExpectedException.none();

    private final static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Test
    public void generateTokenTest() {
        JwtToken jwtToken = new JwtToken();
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("admin");
        userInfo.setTeamId(1);
        String token = jwtToken.generateToken(userInfo);
    }

    @Test
    public void parseTokenTest_with_token_expired_expect_expire_exception() throws InterruptedException {
        excpectedException.expect(ExpiredJwtException.class);

        String token = Jwts.builder()
                .claim("salt", "salt")
                .claim("TeamId", 1)
                .setSubject("admin")
                .setExpiration(new Date(System.currentTimeMillis() + 1000))
                .signWith(key).compact();

        Thread.sleep(2000);

        Claims claims = Jwts.parser().require("salt", "salt")
                .setSigningKey(key).parseClaimsJws(token).getBody();
    }
}