package com.spring.demo.util;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.spring.demo.api.member.Member;
import com.spring.demo.domain.MemberRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenUtils {

    private static final String secretKey = "ThisIsA_SecretKeyForJwtExample";

    public static String generateJwtToken(Member member) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(member.getId())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(createExpireDateForOneYear())
                .signWith(SignatureAlgorithm.HS256, createSigningKey());

        return builder.compact();
    }

    public static boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);
            log.info("expireTime :" + claims.getExpiration());
            log.info("id :" + claims.get("id"));
            log.info("role :" + claims.get("role"));
            return true;

        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private static Date createExpireDateForOneYear() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClaims(Member member) {
        // 공개 클레임에 사용자의 이름과 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", member.getId());
        claims.put("role", member.getRole());

        return claims;
    }

    private static Key createSigningKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    private static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

    private static String getUserIdFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (String) claims.get("id");
    }

    private static MemberRole getRoleFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return (MemberRole) claims.get("role");
    }
}