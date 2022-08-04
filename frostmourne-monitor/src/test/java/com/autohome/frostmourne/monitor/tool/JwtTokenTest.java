package com.autohome.frostmourne.monitor.tool;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.Key;
import java.util.Date;

import com.autohome.frostmourne.monitor.model.account.AccountInfo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

public class JwtTokenTest {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Test
    public void generateTokenTest() {
        JwtToken jwtToken = new JwtToken();
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount("admin");
        accountInfo.setTeamId(1L);
        String token = jwtToken.generateToken(accountInfo);
    }

    @Test
    public void parseTokenTest_with_token_expired_expect_expire_exception() throws InterruptedException {
        String token = Jwts.builder().claim("salt", "salt").claim("TeamId", 1).setSubject("admin").setExpiration(new Date(System.currentTimeMillis() + 1000))
            .signWith(key).compact();

        Thread.sleep(2000);

        assertThrows(ExpiredJwtException.class, () -> Jwts.parser().require("salt", "salt").setSigningKey(key).parseClaimsJws(token).getBody());
    }
}