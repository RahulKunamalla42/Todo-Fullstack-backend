package com.Assignment.TODO.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.validity}")
    private long VALIDITY;

    public String GenarateToken(UserDetails userDetails){
        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .signWith(key())
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .compact();
    }
    public SecretKey key(){
        byte[] decode = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decode);
    }
    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("Invalid or expired token", e);
        }
    }
    public String extractUserName(String token){
        return getClaims(token).getSubject();
    }
    public boolean isValid(String token,UserDetails userDetails){
        return extractUserName(token).equals(userDetails.getUsername()) && !isExiperd(token);
    }
    public boolean isExiperd(String token){
        return getClaims(token).getExpiration().before(Date.from(Instant.now()));
    }
}
