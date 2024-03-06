package br.org.abmnet.abmedicina.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${spring.security.jwt.secret}")
    private String secret;
    @Value("${spring.security.jwt.expiration}")
    private Long expiration;

    public String createToken(String email) {
        return Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }

    public boolean isValidToken(String token) {
        if (token != null) {
            Claims claims = getClaims(token);
            if (claims != null) {
                String email = claims.getSubject();
                Date expirationDate = claims.getExpiration();
                Date now = new Date(System.currentTimeMillis());
                return email != null && expirationDate != null && now.before(expirationDate);
            }
        }
        return false;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token).getBody();
        } catch (Exception exception) {
            return null;
        }
    }
}

