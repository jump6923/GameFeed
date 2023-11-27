package com.sparta.gamefeed.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.gamefeed.common.dto.StatusResponseDto;
import com.sparta.gamefeed.security.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    ObjectMapper ob = new ObjectMapper();

    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenValue = jwtUtil.getJwtFromHeader(request);

        if(StringUtils.hasText(tokenValue)){
            if(!jwtUtil.validateToken(tokenValue)){
                String message = "토큰이 유효하지 않습니다.";
                response.setStatus(400);
                String json = ob.writeValueAsString(new StatusResponseDto(message, response.getStatus()));
                PrintWriter writer = response.getWriter();
                writer.println(json);
                return;
            }

            Claims info = jwtUtil.getUserInforFromToken(tokenValue);

            try {
                setAuthentication(info.getSubject());
            } catch (Exception e){
                log.error(e.getMessage());
                return;
            }
        }

        // 토큰이 없는데 조회를 한다던가 -> 인가
        // 토큰이 있는데 회원가입을 한다던가 / 로그인 한다던가 -> 토큰이 있다 없다는 회원가입을 할 수 있다 없다와는 연관 x 이건 ui/ux가 판단할 일
        // 예외처리를 저희가 해줘야된다고생각하는데

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String username){
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    private Authentication createAuthentication(String username){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, null, null);
    }
}
