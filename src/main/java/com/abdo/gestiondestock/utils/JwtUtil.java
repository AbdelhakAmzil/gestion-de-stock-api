package com.abdo.gestiondestock.utils;

import com.abdo.gestiondestock.model.auth.ExtendedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "your-very-secret-key-at-least-32-chars!!";

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // ------------------------------------------------------------------ extract

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractIdEntreprise(String token) {
        return extractAllClaims(token).get("idEntreprise", String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)   // replaces parseClaimsJws()
                .getPayload();              // replaces getBody()
    }

    // ------------------------------------------------------------------ generate

    public String generateToken(ExtendedUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    private String createToken(Map<String, Object> claims, ExtendedUser userDetails) {
        return Jwts.builder()
                .claims(claims)                                                     // replaces setClaims()
                .subject(userDetails.getUsername())                                 // replaces setSubject()
                .issuedAt(new Date(System.currentTimeMillis()))                     // replaces setIssuedAt()
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // replaces setExpiration()
                .claim("idEntreprise", userDetails.getIdEntreprise().toString())
                .signWith(getSigningKey())                                           // replaces signWith(alg, secret)
                .compact();
    }

    // ------------------------------------------------------------------ validate

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}