package com.walmart.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET = "mysecretkeymysecretkeymysecretkey12";
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username){
        // 1 hours
        long EXPIRED_TIME = 1000 * 60;
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return extractClaims(token).getSubject();
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token){
         return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
