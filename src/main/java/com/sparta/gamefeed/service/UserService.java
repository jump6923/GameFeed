package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.IntroduceRequestDto;
import com.sparta.gamefeed.dto.SignupRequestDto;
import com.sparta.gamefeed.dto.ProfileResponseDto;
import com.sparta.gamefeed.dto.StatusResponseDto;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if(introduce == null){
            introduce = "자기 소개를 입력해 주세요";
        }

        Optional<User> checkUserId = userRepository.findByUserId(userId);
        if(checkUserId.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(userId, password, email, introduce);
        userRepository.save(user);
    }

    public ProfileResponseDto getUser(Long id) {
        User profileUser = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 id에 회원이 존재하지 않습니다."));

        return new ProfileResponseDto(profileUser);
    }

    @Transactional
    public ResponseEntity<?> changeUserInfo(IntroduceRequestDto requestDto, Long id) {
        User changeUser = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당하는 id에 회원이 존재하지 않습니다."));

        if(requestDto.getChangePassword() != null){
            if(requestDto.getOriginPassword() == null){
                throw new NullPointerException("기존 비밀번호를 입력해 주세요");
            }
            if(passwordEncoder.matches(requestDto.getOriginPassword(), changeUser.getPassword())){
                requestDto.setChangePassword(passwordEncoder.encode(requestDto.getChangePassword()));
                changeUser.changeUserInfo(requestDto);
                return ResponseEntity.ok(new StatusResponseDto("비밀번호 변경", HttpStatus.OK.value()));
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        }
        changeUser.changeUserInfo(requestDto);
        return ResponseEntity.ok(new ProfileResponseDto(changeUser));
    }
}
