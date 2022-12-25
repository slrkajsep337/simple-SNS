package com.example.finalprojectsujin221220.configuration;

import com.example.finalprojectsujin221220.service.UserService;
import com.example.finalprojectsujin221220.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final UserService us;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader: {}", authorizationHeader);

        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("authorizationHeader is null or invalid format");
            filterChain.doFilter(request, response);
            return;
        }

        String token;
        token = authorizationHeader.split(" ")[1];
        try{
            token = authorizationHeader.split(" ")[1];
        } catch (Exception e) {
            log.error(("bringing token is fail"));
            filterChain.doFilter(request, response);
            return;
        }

        if(JwtUtil.isExpired(token, secretKey)) {
            log.error(("token is expired"));
            filterChain.doFilter(request, response);
            return;
        }

        String userName = JwtUtil.getUserName(token, secretKey);
        log.info("UserName : {} ", userName);

        //권한부여
        UsernamePasswordAuthenticationToken authenticToken = new UsernamePasswordAuthenticationToken(
                userName, null, List.of(new SimpleGrantedAuthority("USER")));

        authenticToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticToken);
        filterChain.doFilter(request, response);

    }
}
