package devbhdn.photoblock.controller;

import devbhdn.photoblock.dto.UserEditProfileRequestDto;
import devbhdn.photoblock.dto.UserEditUsernameRequestDto;
import devbhdn.photoblock.dto.UserEditUsernameResponseDto;
import devbhdn.photoblock.dto.UserResponseDto;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/edit")
    public UserResponseDto editProfile(
            @RequestBody @Valid UserEditProfileRequestDto requestDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return userService.editProfile(requestDto, user.getId());
    }

    @PatchMapping("/change-username")
    public UserEditUsernameResponseDto UserResponseDto(
            @RequestBody @Valid UserEditUsernameRequestDto requestDto,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        return userService.changeUsername(requestDto, user.getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable Long id) {
        userService.deleteAccount(id);
    }

    @GetMapping("/me")
    public UserResponseDto getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getProfile(user.getId());
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserProfile(@PathVariable Long id) {
        return userService.getProfile(id);
    }
}
