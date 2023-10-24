package com.group2.secotool_app.configuration.security;

import com.group2.secotool_app.bussiness.service.Impl.UserServiceImpl;
import com.group2.secotool_app.model.entity.User;
import com.group2.secotool_app.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserServiceImpl userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = getAuthTokenFromReq(request);
        if (token == null){
            filterChain.doFilter(request,response);
            return;
        }
        String email = jwtUtils.extractUsername(token);
        String id = jwtUtils.extractUserID(token);
        if (email != null && id != null && SecurityContextHolder.getContext().getAuthentication() == null){
            User user = userService.findUserByUsername(email);
            if (jwtUtils.isTokenValid(token,user)){
                UsernamePasswordAuthenticationToken authenticationToken  = new UsernamePasswordAuthenticationToken(user,id,user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
    private String getAuthTokenFromReq(HttpServletRequest request){
        String authorizarionHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authorizarionHeader)){
            String token = authorizarionHeader;
            if (authorizarionHeader.startsWith("Bearer "))
                token = authorizarionHeader.substring(7);
            return token;
        }
        return null;
    }
}
