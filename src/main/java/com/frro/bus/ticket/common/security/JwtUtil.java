package com.frro.bus.ticket.common.security;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PreDestroy;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil {

    private final SecretKey key;
    private final long expirationMs;
    private final Set<String> blacklistedTokens = ConcurrentHashMap.newKeySet();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    public JwtUtil(
            @Value("${app.jwtSecret}") String secret,
            @Value("${app.jwtExpirationMs}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationMs;
    }

    public String generateToken(int userId, String email, boolean isAdmin) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("email", email)
                .claim("isAdmin", isAdmin)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public Claims validateAndExtractClaims(String token) {
        if (blacklistedTokens.contains(token)) {
            throw new IllegalArgumentException("Token is blacklisted");
        }
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public void blacklistToken(String token) {
        long expiry;
        try {
            expiry = extractClaims(token).getExpiration().getTime();
        } catch (Exception e) {
            // token is already invalid, no need to blacklist
            log.warn("Could not blacklist token, it may already be invalid: {}", e.getMessage());
            return;
        }
        if (!blacklistedTokens.contains(token)) {
            blacklistedTokens.add(token);
        }
        long delay = expiry - System.currentTimeMillis();
        if (delay > 0) {
            scheduler.schedule(() -> blacklistedTokens.remove(token), delay, TimeUnit.MILLISECONDS);
        }
    }

    public Claims extractClaims(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public int extractUserId(String token) {
        return Integer.parseInt(extractClaims(token).getSubject());
    }

    public String extractEmail(String token) {
        return extractClaims(token).get("email", String.class);
    }

    public boolean extractIsAdmin(String token) {
        return extractClaims(token).get("isAdmin", Boolean.class);
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    @PreDestroy
    public void destroy() {
        scheduler.shutdown();
    }
}
