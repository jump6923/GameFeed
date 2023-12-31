package com.sparta.gamefeed.email.controller;

import com.sparta.gamefeed.email.dto.CodeRequestDto;
import com.sparta.gamefeed.common.dto.StatusResponseDto;
import com.sparta.gamefeed.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    @PatchMapping("/email/checkemail")
    ResponseEntity<?> emailCheck(@RequestBody CodeRequestDto requestDto){
        try {
            emailService.emailCheck(requestDto.getCode());
            return ResponseEntity.ok(new StatusResponseDto("이메일 인증이 완료됐습니다.", HttpStatus.OK.value()));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(new StatusResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}