package th.co.erp.sme.util;

import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JwtHelper {
    private final String issuer;
    private final String jwtSecret;

    public JwtHelper(String issuer, String jwtSecret) {
        this.issuer = issuer;
        this.jwtSecret = jwtSecret;
    }

    public String generateAppToken(String username, List<String> role, long durationMinutes) {
        var claims = new HashMap<String, Object>();
        claims.put("username", username);
        claims.put("role", role);
        return generateToken(issuer, durationMinutes, claims);
    }

    public String generateAppToken(String username, String body, List<String> role, long durationMinutes) {
        var claims = new HashMap<String, Object>();
        claims.put("username", username);
        claims.put("body", body);
        claims.put("role", role);
        return generateToken(issuer, durationMinutes, claims);
    }

    public String generateToken(String issuer, long durationMinutes, Map<String, Object> claims) {
        SecretKey key = getSecretKey();

        return Jwts.builder()
                .header().type("JWT").and()
                .issuer(issuer)
                .claims(Optional.ofNullable(claims).orElseGet(HashMap::new))
                .expiration(getExpiration(durationMinutes))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public Jws<Claims> verifyToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (Exception e) {
            throw new JwtException("VerifyToken failed", e);
        }
    }

    public Jws<Claims> verifyToken(String token, String jwtSecret) {
        try {
            return Jwts.parser()
                    .verifyWith(toSecretKey(jwtSecret))
                    .build()
                    .parseSignedClaims(token);
        } catch (Exception e) {
            throw new JwtException("VerifyToken failed", e);
        }
    }

    public Map<String, String> parseBody(String token) throws IOException {
        String bodyJson = new String(Base64.getUrlDecoder().decode(token.split("\\.")[1]), StandardCharsets.UTF_8);
        return JsonHelper.objectToObjectTypeRef(bodyJson, new TypeReference<>() {});
    }

    public Date getExpiration(long minutes) {
        return Date.from(Instant.now().plus(Duration.ofMinutes(minutes)));
    }

    private SecretKey getSecretKey() {
        return toSecretKey(this.jwtSecret);
    }

    private SecretKey toSecretKey(String secret) {

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public byte[] decodeSecret(String secret) {
        return Base64.getDecoder().decode(secret);
    }
}
