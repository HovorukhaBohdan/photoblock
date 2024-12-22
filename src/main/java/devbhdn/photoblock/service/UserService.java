package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.UserEditProfileRequestDto;
import devbhdn.photoblock.dto.UserEditUsernameRequestDto;
import devbhdn.photoblock.dto.UserEditUsernameResponseDto;
import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserResponseDto editProfile(UserEditProfileRequestDto requestDto, Long id);

    UserEditUsernameResponseDto changeUsername(UserEditUsernameRequestDto requestDto, Long id);

    void deleteAccount(Long id);

    UserResponseDto getProfile(Long id);
}
