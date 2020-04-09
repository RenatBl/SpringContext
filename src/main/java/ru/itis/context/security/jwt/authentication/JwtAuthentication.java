package ru.itis.context.security.jwt.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.context.security.jwt.details.UserDetailsImpl;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private boolean isAuthenticated = false;
    private String token;
    private UserDetails userDetails;

    private static final String secret = "secretKey";

    public JwtAuthentication(String token) {
        this.token = token;
        if (token != null) {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            this.userDetails = UserDetailsImpl.builder()
                    .userId(Long.valueOf(claims.getSubject()))
                    .username(claims.get("name", String.class))
                    .role(claims.get("role", String.class))
                    .status(claims.get("status", String.class))
                    .build();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.token;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
