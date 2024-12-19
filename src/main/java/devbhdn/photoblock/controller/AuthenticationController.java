package devbhdn.photoblock.controller;

import devbhdn.photoblock.dto.UserLoginRequestDto;
import devbhdn.photoblock.dto.UserLoginResponseDto;
import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @PostMapping("/login")
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        return null;
    }

    @PostMapping("/register")
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        return null;
    }
}

