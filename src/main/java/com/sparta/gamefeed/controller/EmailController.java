package com.sparta.gamefeed.controller;

import com.sparta.gamefeed.dto.CodeRequestDto;
import com.sparta.gamefeed.dto.EmailRequestDto;
import com.sparta.gamefeed.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmailController {

    private final EmailService emailService;

    @PatchMapping("/email/checkemail")
    public void emailCheck(@RequestBody CodeRequestDto requestDto){
        emailService.emailCheck(requestDto.getCode());
    }
}