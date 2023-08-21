package com.decimal.rbac.util;

import com.decimal.rbac.model.dtos.RoleDto;
import com.decimal.rbac.model.dtos.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    private static final String SECRET = "Secret";
    private static final Long EXPIRATION = 36000000L;// EXPIRATION IN MILLIS ~ 10 Hours
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static String generateToken(UserDto user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",user.getId());
        if (null != user.getRoles() && !user.getRoles().isEmpty()) {
            claims.put("roles", user.getRoles().stream().map(RoleDto::getName).toList());
        }
        if (null != user.getRoleGroups() && !user.getRoleGroups().isEmpty()) {
            claims.put("roleGroups", user.getRoleGroups().stream().map(g -> g.get("name")).toList());
        }
        if (null != user.getUserGroups() && !user.getUserGroups().isEmpty()) {
            claims.put("userGroups", user.getUserGroups().stream().map(g -> g.get("name")).toList());
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUserName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(signatureAlgorithm, SECRET).compact();
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    private static String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private static Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }

    public static boolean validateToken(String token, UserDto userDto) {
        final String userName = extractUserName(token);
        return (userName!=null && userName.equals(userDto.getUserName()) && !isTokenExpired(token));
    }
}
