package com.example.ecommercebackend.Security;

import com.example.ecommercebackend.Entities.User.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String generateAccessToken(User user, int role_id) {
        String role_id_str = "";
        switch (role_id) {
            case 1:
                role_id_str = "ROLE_ADMIN";
                break;
            case 2:
                role_id_str = "ROLE_USER";
                break;
            default:
                role_id_str = "ROLE_USER";
                break;
        }
        return Jwts.builder()
                .setSubject(String.format("%s", user.getUser_id()))
                .setIssuer("Talama")
                .claim("id",user.getUser_id())
                .claim("roles", role_id_str)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    public boolean validateAccessToken(String token) {

        try {
            var a = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }

        return false;
    }

    public String getRoleFromToken(String token) {
        try {
            Jws<Claims> a = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            var body = a.getBody();
            String role_str = (String) body.get("roles");
            return role_str;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
