package top.mikevane.ljserver.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * @author: whb
 * @date: 2022-10-05-21-42
 * @version: 1.0
 */
public class JwtUtil {
    /**
     * 一天
     */
    private static long time = 1000*60*60*24;

    private static String signature = "admin";

    public static String createToken(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .claim("username", "admin")
                .claim("role", "admin")
                .setSubject("adminTest")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }
}
