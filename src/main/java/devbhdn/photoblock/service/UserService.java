package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.*;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);

    UserResponseDto editProfile(UserEditProfileRequestDto requestDto, Long id);

    UserEditUsernameResponseDto changeUsername(UserEditUsernameRequestDto requestDto, Long id);

    void deleteAccount(Long id);

    UserResponseDto getProfile(Long id);
}
