package com.sparta.gamefeed.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.gamefeed.user.dto.LoginRequestDto;
import com.sparta.gamefeed.common.dto.StatusResponseDto;
import com.sparta.gamefeed.security.jwt.JwtUtil;
import com.sparta.gamefeed.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;


@Slf4j(topic = "로그인 및 JWT 생성")
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;
    private final ObjectMapper ob = new ObjectMapper();

    @Autowired
    private UserService userService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            if(!userService.checkUserEmail(requestDto)){
                String message = "이메일 인증을 하지 않았습니다.";
                response.setStatus(400);
                String json = ob.writeValueAsString(new StatusResponseDto(message, response.getStatus()));
                PrintWriter writer = response.getWriter();
                writer.print(json);
                throw new IllegalArgumentException("이메일 인증을 하지 않았습니다");
            }

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException | IllegalArgumentException e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        String token = jwtUtil.createToken(username);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);

        String message = "로그인에 성공했습니다.";
        response.setStatus(200);
        String json = ob.writeValueAsString(new StatusResponseDto(message, response.getStatus()));
        PrintWriter writer = response.getWriter();

        writer.println(json);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        String message = "로그인에 실패했습니다.";
        response.setStatus(400);
        String json = ob.writeValueAsString(new StatusResponseDto(message, response.getStatus()));
        PrintWriter writer = response.getWriter();

        writer.println(json);
    }
}
