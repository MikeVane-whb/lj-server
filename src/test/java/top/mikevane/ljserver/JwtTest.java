package top.mikevane.ljserver;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;
import java.util.Vector;

/**
 * 测试 jwt 的加密与解密
 * @author: whb
 * @date: 2022-10-05-19-23
 * @version: 1.0
 */
@Slf4j
public class JwtTest {

    /**
     * 一天
     */
    private long time = 1000*60*60*24;

    private String signature = "admin";

    /**
     * 加密
     */
    @Test
    public void jwt(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", "tom")
                .claim("role", "admin")
                .setSubject("adminTest")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        log.info(jwtToken);
    }

    /**
     * 解密
     */
    @Test
    public void parse(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRvbSIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluVGVzdCIsImV4cCI6MTY2NTA1NjA3NCwianRpIjoiMmQzMjc1MjEtNzQ1My00Y2IzLTkzYWQtYWI4NDZmYjY3ZTVhIn0.MuMQ4_6vAJu9XX3Bg72r9rDkUs806sWfOoHbAkJpl7c";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
    }
}
