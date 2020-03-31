package com.mytest.springsecuritydemo2.daotest;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * jwt
 * header: {'typ': 'JWT','alg': 'HS256'}
 * payload:{iss: jwt签发者
 * sub: 主题
 * aud: 接收jwt的一方
 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
 * nbf: 生效时间
 * iat: 签发时间
 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
 * }
 * Signature:{header (base64后的),payload (base64后的),secret}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JwtUtilTest {

    //key的长度至少是4个字节
    private static final String secretKey = "jwt_secret";

    @Test
    public void create_token() {
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000 * 6000;//过期时间为1分钟
        JwtBuilder builder = Jwts.builder()
                .setId("888")
                .setSubject("me")
                .setIssuer("me")
                .setIssuedAt(new Date())
//                .setExpiration(new Date(exp))
                .claim("roles", "admin,user")
                .claim("username", "jwt")
                .signWith(SignatureAlgorithm.HS256, secretKey);
        log.info(builder.compact());
    }

    @Test
    public void parse_token() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiJtZSIsImlzcyI6Im1lIiwiaWF0IjoxNTg1Mjc1MzYyLCJyb2xlcyI6ImFkbWluLHVzZXIiLCJ1c2VybmFtZSI6Imp3dCJ9.i4qOrk3lDynaqw49rnJcoXZbprmpRxhyC1XjP3iVKi0";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody();
        printJwtClaims(claims);
    }

    @Test
    public void create_custom_claims_token() {
        Map<String, Object> claims = new HashMap<>();
        String userId = "100000000001";
        List<String> permissionList = new ArrayList<>();
        permissionList.add("sys:create");
        permissionList.add("sys:update");
        permissionList.add("sys:delete");
        permissionList.add("auth:create");
        claims.put("userid", userId);
        claims.put("permission", permissionList);
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject("me")
                .setIssuer("me")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey);
        log.info(builder.compact());
    }

    @Test
    public void parse_custom_claims_token() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZSIsImlzcyI6Im1lIiwicGVybWlzc2lvbiI6WyJzeXM6Y3JlYXRlIiwic3lzOnVwZGF0ZSIsInN5czpkZWxldGUiLCJhdXRoOmNyZWF0ZSJdLCJ1c2VyaWQiOiIxMDAwMDAwMDAwMDEiLCJpYXQiOjE1ODUyNzM5NjR9.HxK6z7UrLZG1h02cOwy5jpUenPB_4G_78WStelGbXbY";
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody();
        printJwtClaims(claims);
    }

    @Test
    public void verify_jwt_token() {
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtZSIsImlzcyI6Im1lIiwicGVybWlzc2lvbiI6WyJzeXM6Y3JlYXRlIiwic3lzOnVwZGF0ZSIsInN5czpkZWxldGUiLCJhdXRoOmNyZWF0ZSJdLCJ1c2VyaWQiOiIxMDAwMDAwMDAwMDEiLCJpYXQiOjE1ODUyNzM5NjR9.HxK6z7UrLZG1h02cOwy5jpUenPB_4G_78WStelGbXbY";
        Claims claims;
        try {
            claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(secretKey)         //设置签名的秘钥
                    .parseClaimsJws(jwtToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("jwt token 已过期");
            claims = null;
        } catch (Exception e) {
            claims = null;
        }//设置需要解析的jwt
        if (claims == null) {
            log.info("jwt token无效");
        } else {
            printJwtClaims(claims);
        }
    }

    private void printJwtClaims(Claims claims) {
        Set<String> keys = claims.keySet();
        for (String key : keys) {
            log.info(key + "=" + claims.get(key));
        }
    }
}
