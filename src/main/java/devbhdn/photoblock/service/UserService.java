package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.user.UserEditProfileRequestDto;
import devbhdn.photoblock.dto.user.UserEditUsernameRequestDto;
import devbhdn.photoblock.dto.user.UserEditUsernameResponseDto;
import devbhdn.photoblock.dto.user.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.user.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserResponseDto editProfile(UserEditProfileRequestDto requestDto, Long id);

    UserEditUsernameResponseDto changeUsername(UserEditUsernameRequestDto requestDto, Long id);

    void deleteAccount(Long id);

    UserResponseDto getProfile(Long id);
}
