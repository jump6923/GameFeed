package com.sparta.gamefeed.user.controller;

import com.sparta.gamefeed.common.dto.StatusResponseDto;
import com.sparta.gamefeed.security.UserDetailsImpl;
import com.sparta.gamefeed.email.service.EmailService;
import com.sparta.gamefeed.user.dto.IntroduceRequestDto;
import com.sparta.gamefeed.user.dto.PasswordRequestDto;
import com.sparta.gamefeed.user.dto.ProfileResponseDto;
import com.sparta.gamefeed.user.dto.SignupRequestDto;
import com.sparta.gamefeed.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            for(FieldError fieldError : bindingResult.getFieldErrors()){
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            String message = "모든 칸을 채우지 않았거나, 조건에 맞지 않습니다.";
            return ResponseEntity.badRequest().body(new StatusResponseDto(message, HttpStatus.BAD_REQUEST.value()));
        }
        try {
            userService.signup(requestDto);
            emailService.sendSimpleMessage(requestDto);
            String message = "이메일이 전송되었습니다! 이메일 코드를 확인해주세요!";
            return ResponseEntity.ok().body(new StatusResponseDto(message, HttpStatus.OK.value()));
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(new StatusResponseDto(ex.getMessage(),HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/user/profile")
    public ProfileResponseDto getUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getUser(userDetails.getUser().getId());
    }

    @PatchMapping("/user/profile")
    public ResponseEntity<?> changeUserInfo(@RequestBody IntroduceRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            return ResponseEntity.ok(userService.changeUserInfo(requestDto,userDetails.getUser().getId()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            return ResponseEntity.badRequest().body(new StatusResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PatchMapping("/user/profile/password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        try {
            userService.changePassword(requestDto,userDetails.getUser().getId());
            return ResponseEntity.ok(new StatusResponseDto("비밀번호 변경 완료", HttpStatus.OK.value()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            return ResponseEntity.badRequest().body(new StatusResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
