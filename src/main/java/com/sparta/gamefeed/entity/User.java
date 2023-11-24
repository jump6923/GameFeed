package com.sparta.gamefeed.entity;

import com.sparta.gamefeed.dto.IntroduceRequestDto;
import com.sparta.gamefeed.dto.PasswordRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 15)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = true)
    private String introduce;

    @Column(nullable = false)
    private boolean checker;

    public User(String username, String password, String email, String introduce, boolean checker) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduce = introduce;
        this.checker = checker;
    }

    public void changeUserInfo(IntroduceRequestDto requestDto) {
        if(requestDto.getEmail() != null){
            this.email = requestDto.getEmail();
        }

        if(requestDto.getIntroduce() != null){
            this.introduce = requestDto.getIntroduce();
        }
    }

    public void changePassword(PasswordRequestDto requestDto){
        if(requestDto.getChangePassword() != null){
            this.password = requestDto.getChangePassword();
        }
    }

    public void changechecker(boolean checker) {
        this.checker = checker;
    }
}
