package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.UserRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserResponseDto editProfile(UserRequestDto requestDto, String username);
}
