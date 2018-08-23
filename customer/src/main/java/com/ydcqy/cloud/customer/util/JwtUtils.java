package com.ydcqy.cloud.customer.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static final String SIGN           = "sdkgli1o24puKDHGkij1glk32x323pi2";
    private static final long   DEFAULT_EXPIRE = 30 * 60 * 1000;

    public static String createJwt(Map<String, Object> params) {
        return createJwt(params, DEFAULT_EXPIRE);
    }

    public static String createJwt(Map<String, Object> params, long expireMillis) {
        long currMillis = System.currentTimeMillis();
        JwtBuilder jwt = Jwts.builder().setClaims(params).setIssuedAt(new Date(currMillis)).signWith(SignatureAlgorithm.HS256, generateSecretKey());
        if (expireMillis > 0) {
            jwt.setExpiration(new Date(expireMillis + currMillis));
        }
        return jwt.compact();
    }

    public static Map<String, Object> parseJwt(String jwt) {
        return Jwts.parser().setSigningKey(generateSecretKey()).parseClaimsJws(jwt).getBody();
    }

    private static Key generateSecretKey() {
        return new SecretKeySpec(SIGN.getBytes(), "AES");
    }

}
