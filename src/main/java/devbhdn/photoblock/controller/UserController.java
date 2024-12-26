package devbhdn.photoblock.controller;

import devbhdn.photoblock.dto.user.UserEditProfileRequestDto;
import devbhdn.photoblock.dto.user.UserEditUsernameRequestDto;
import devbhdn.photoblock.dto.user.UserEditUsernameResponseDto;
import devbhdn.photoblock.dto.user.UserResponseDto;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public UserEditUsernameResponseDto changeUsername(
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
