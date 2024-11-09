package com.example.catfactsdaily.service;

import com.example.catfactsdaily.entity.TokenBlacklisted;
import com.example.catfactsdaily.repository.TokenBlacklistedRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private Long jwtExpiration;

    private final TokenBlacklistedRepository tokenBlacklistedRepository;

    @Autowired
    public JwtService(TokenBlacklistedRepository tokenBlacklistedRepository) {
        this.tokenBlacklistedRepository = tokenBlacklistedRepository;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails, Integer userId) {
        return generateToken(new HashMap<>(), userDetails, userId);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails, Integer userId) {
        extraClaims.put("userId", userId);
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        Integer userId = claims.get("userId", Integer.class);
        logger.info("Extracted userId from token: {}", userId);
        return userId;
    }

    public Integer extractUserIdFromPrincipal(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken authToken) {
            String token = (String) authToken.getDetails();
            Integer userId = extractUserId(token);
            logger.info("Extracted userId from principal: {}", userId);
            return userId;
        }
        throw new IllegalArgumentException("Invalid principal");
    }

    public Long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            Long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && !isTokenBlacklisted(token);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void invalidateToken(String token) {
        Date expirationDate = extractExpiration(token);

        TokenBlacklisted blacklistedToken = new TokenBlacklisted();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpirationDate(expirationDate);

        tokenBlacklistedRepository.save(blacklistedToken);
    }

    private Boolean isTokenBlacklisted(String token) {
        return tokenBlacklistedRepository.findByToken(token).isPresent();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
