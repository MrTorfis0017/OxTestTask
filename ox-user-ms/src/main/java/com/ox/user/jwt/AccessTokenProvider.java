package com.ox.user.jwt;

import com.ox.user.dto.TokenType;
import com.ox.user.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ox.user.entities.Role;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccessTokenProvider extends TokenProvider {

    @Value("${jwt.tokenExpire}")
    private Long exp;

    @Override
    public String generateToken(User user) {
        return createToken(formClaims(user), user.getEmail(), exp * 60 * 1000);
    }
    private Map<String, Object> formClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        // TODO check firstName
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("middleName", user.getMiddleName());
        claims.put("roles", user.getRoles().stream().map(Role::getName).toList());
        claims.put("type", TokenType.ACCESS);
        return claims;
    }
}
