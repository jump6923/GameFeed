package com.sparta.gamefeed.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Size(min = 3)
    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Size(min = 4)
    private String password;

    @NotBlank
    @Email
    private String email;

    private String introduce;
}
