package devbhdn.photoblock.controller;

import devbhdn.photoblock.dto.UserRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;
import devbhdn.photoblock.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/edit")
    public UserResponseDto editProfile(
            @RequestBody @Valid UserRequestDto requestDto,
            Authentication authentication
    ) {
        String username = authentication.getName();
        return userService.editProfile(requestDto, username);
    }
}
