package com.mytest.springsecuritydemo3.common.util;

import com.mytest.springsecuritydemo3.common.sercurity.JwtTokenVerifyResult;
import com.mytest.springsecuritydemo3.model.dto.UserDetailsDTO;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    public static final String JWT_TOKEN_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer";

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USER_ID = "userId";
    private static final String CLAIM_KEY_PERMISSION = "permission";
    private static final String JWT_SECURITY_SECRET = "jwt_security_secret";
    private static final String CLAIM_VALUE_ISS = "security";
    private static final String CLAIM_VALUE_SUB = "anyone";
    //默认过期时间为1小时
    private static final int DEFAULT_EXPIRATION_MINUTES = 60;

    /**
     * 封装用户信息，并生成token
     *
     * @param userDetails
     * @return
     */
    public String createToken(UserDetailsDTO userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, userDetails.getUserId());
        claims.put(CLAIM_KEY_PERMISSION, userDetails.getPermission());
        return this.createToken(claims, DEFAULT_EXPIRATION_MINUTES);
    }

    public String createToken(UserDetailsDTO userDetails, int expiredMinutes) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, userDetails.getUserId());
        claims.put(CLAIM_KEY_PERMISSION, userDetails.getPermission());
        return this.createToken(claims, expiredMinutes);
    }

    /**
     * 根据负载生成token
     *
     * @param claims
     * @return
     */
    public String createToken(Map<String, Object> claims, int expireMinutes) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(CLAIM_VALUE_ISS)
                .setSubject(CLAIM_VALUE_SUB)
                .setIssuedAt(new Date())
                .setExpiration(this.getExpirationDate(expireMinutes))
                .signWith(SignatureAlgorithm.HS512, JWT_SECURITY_SECRET)
                .compact();
    }

    /**
     * 解析token，获取负载主体
     *
     * @param jwtToken
     * @return
     */
    public JwtTokenVerifyResult verifyToken(String jwtToken) {
        JwtTokenVerifyResult result = new JwtTokenVerifyResult();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_SECURITY_SECRET)
                    .parseClaimsJws(jwtToken)
                    .getBody();
            result.setSuccess(true);
            result.setData(claims);
        } catch (ExpiredJwtException e1) {
            result.setErrMsg("token 已过期:" + e1.getMessage());
        } catch (UnsupportedJwtException | MalformedJwtException e2) {
            result.setErrMsg("token 格式错误:" + e2.getMessage());
        } catch (SignatureException e3) {
            result.setErrMsg("token 签名错误:" + e3.getMessage());
        } catch (Exception e4) {
            result.setErrMsg("token 无效：" + e4.getMessage());
        }
        if (!result.isSuccess()) {
            logger.warn("JWT验证失败:{},错误信息:{}", jwtToken, result.getErrMsg());
        }
        return result;
    }

    /**
     * 解析token，获取负载主体中的用户名
     *
     * @param jwtToken
     * @return
     */
    public String getUserIdFromJwtToken(String jwtToken) {
        String userId = null;
        JwtTokenVerifyResult result = this.verifyToken(jwtToken);
        if (result.isSuccess()) {
            Claims claims = result.getData();
            userId = claims.get(CLAIM_KEY_USER_ID).toString();
        }
        return userId;
    }

    /**
     * 生成token的过期时间
     *
     * @return
     */
    public Date getExpirationDate(int minutes) {
        return new Date(System.currentTimeMillis() + minutes * 60 * 1000);
    }

    /**
     * 验证token是否有效
     *
     * @param jwtToken
     * @param userDetails
     * @return
     */
    public boolean validateToken(String jwtToken, UserDetailsDTO userDetails) {
        String userId = this.getUserIdFromJwtToken(jwtToken);
        if (StringUtils.isEmpty(userId)) {
            return false;
        }
        if (userId.equals(userDetails.getUserId()) && !this.isTokenExpired(jwtToken)) {
            return true;
        }
        return false;
    }

    /**
     * 验证token是否过期
     *
     * @param jwtToken
     * @return
     */
    public boolean isTokenExpired(String jwtToken) {
        JwtTokenVerifyResult result = this.verifyToken(jwtToken);
        if (result.isSuccess()) {
            Claims claims = result.getData();
            Date expired = claims.getExpiration();
            return new Date().before(expired);
        }
        return true;
    }

    /**
     * 刷新token
     *
     * @param jwtToken
     * @return
     */
    public String refreshToken(String jwtToken) {
        if (!this.isTokenExpired(jwtToken)) {
            return null;
        }
        JwtTokenVerifyResult result = this.verifyToken(jwtToken);
        Claims claims = result.getData();
        return this.createToken(claims, DEFAULT_EXPIRATION_MINUTES);
    }
}
