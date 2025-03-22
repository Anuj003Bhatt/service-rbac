package com.decimal.rbac.filter;

import com.decimal.rbac.exceptions.AuthenticationFailedException;
import com.decimal.rbac.model.security.UserContext;
import com.decimal.rbac.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ROLES_CLAIM = "roles";
    private static final String USER_ID_CLAIM = "roles";

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        final String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String username = null;
        Claims claims = null;
        String jwt;

        if (null != authorization && authorization.startsWith("Bearer ")) {
            jwt = authorization.substring(7);
            claims = JwtUtil.extractAllClaims(jwt);
            username = claims.getSubject();
        }
        if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<String> roles = getRolesFromClaims(claims);
            List<GrantedAuthority> authorities = generateAuthoritiesFromClaims(roles);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    getAuthorizedUserContext(claims, roles),
                    null,
                    authorities
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private UserContext getAuthorizedUserContext(Claims claims, List<String> roles) {
        return UserContext.builder()
                .userId(UUID.fromString(claims.get(USER_ID_CLAIM).toString()))
                .roles(roles)
                .build();
    }
    private List<String> getRolesFromClaims(Claims claims) {
        if (claims.get(ROLES_CLAIM) == null || !(claims.get(ROLES_CLAIM) instanceof List<?>)) {
            throw new AuthenticationFailedException("Unauthorized access");
        }
        return ((List<?>) claims.get(ROLES_CLAIM)).stream().map(Object::toString).toList();
    }

    public static List<GrantedAuthority> generateAuthoritiesFromClaims(List<String> roles) {
        return new ArrayList<>(roles.stream().map(r -> (GrantedAuthority) () -> r).toList());
    }
}