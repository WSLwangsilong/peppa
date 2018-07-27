package com.cjt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author caojiantao
 */
@Service
public class TokenService {

    private static final Logger logger = LogManager.getLogger(TokenService.class);

    @Value("${token_secret}")
    private String secret;

    @Value("${token_maxAge}")
    private long maxAge;

    /**
     * 生成token（注意注意：key为exp必须为非负数！！！）
     */
    public String getToken(int id, boolean rememberMe) {
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder builder = JWT.create();
            builder.withClaim("userId", id);
            if (rememberMe) {
                builder.withExpiresAt(new Date(System.currentTimeMillis() + maxAge));
            }
            token = builder.sign(algorithm);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return token;
    }

    /**
     * 解析token，得到userId
     */
    public int parseToken(String token) {
        int userId = 0;
        if (StringUtils.isNotBlank(token)) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                userId = jwt.getClaim("userId").asInt();
            } catch (JWTVerificationException | UnsupportedEncodingException e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return userId;
    }
}
