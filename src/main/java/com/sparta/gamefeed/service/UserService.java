package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.SignupRequestDto;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void signup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String introduce = requestDto.getIntroduce();

        if(introduce.isEmpty()){
            introduce = "자기 소개를 입력해 주세요";
        }

        Optional<User> checkUserId = userRepository.findByUserid(userId);
        if(checkUserId.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(userId, password, email, introduce);
        userRepository.save(user);
    }
}
