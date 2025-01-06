package qianz.cloudapicommon.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import qianz.cloudapicommon.exception.ParamInvalidException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Jwt工具类
 */
@Slf4j
public class JwtUtil {
    public static final Long JWT_TTL = 24 * 3600000L; // 3600000为1天
    public static final String JWT_KEY = "Qianz";
    private static final String secret = "~AMg)YBB=xx4UnlU=*OBX|+L@ys[hHJiv6Wkm_U`dV04+S55/x>g7GUXhpIR>Krx";
    private static final SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

    /**
     * 创建一个jwt令牌
     *
     * @param userId 用户id
     * @return Jwt令牌
     */
    public static String creatToken(Long userId) {
        return createToken(userId, null);
    }

    /**
     * 校验令牌是否合法没过期
     *
     * @param token jwt令牌
     * @return 是否合法
     * */
    public static boolean verifyToken(String token) {
        if (token == null) throw new ParamInvalidException("JWT令牌为空");
        if (token.startsWith("bearer ")) {
            token = token.substring(7);
        }
        Claims claims = getTokenClaims(token);
        if (claims == null) return false; // 解析出错
        if (claims.getExpiration().before(new Date())) return false; // 令牌过期
        return claims.getIssuer().equals(JWT_KEY);
    }

    /**
     * 从jwt令牌中获得id
     *
     * @param token jwt令牌
     * @return userid
     * */
    public static Long getUserId(String token) {
        Claims claims = getTokenClaims(token);
        if (claims != null) {
            return Long.parseLong(claims.get("sub").toString());
        }
        return null;
    }

    private static String createToken(Object subject, Map<String, Object> claims) {
        try {
            return "bearer " + Jwts.builder()
                    .subject(subject.toString()) // 主题
                    .id(UUID.randomUUID().toString())
                    .issuer(JWT_KEY)
                    .issuedAt(new Date())
                    .claims(claims)
                    .expiration(new Date(System.currentTimeMillis() + JWT_TTL))
                    .signWith(key)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Claims getTokenClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            log.warn("令牌解析错误:{}", token);
            return null;
        }
    }

    public static Long getUserId(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.toLowerCase().startsWith("bearer ")) {
            return null;
        }
        return getUserId(authorizationHeader.substring("bearer ".length()).trim());
    }
}
