package in.project.moneymanager.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000L * 60 * 60 * 24 * 30; // 30 days

    // We will still read the secret string from properties
    @Value("${jwt.secret}")
    private String secret;

    // 1. Declare the final Key field
    private final Key signingKey;

    // 2. Add a constructor to initialize the key right after @Value is injected
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // We use the parameter 'secret' here, which Spring injects
        this.secret = secret;
        this.signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    // 3. Simple getter for the key (no more decoding every time)
    private Key getSigningKey() {
        return signingKey;
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return extractedUsername.equals(username) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Uses the initialized key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
