package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.user.UserLoginRequestDto;
import devbhdn.photoblock.dto.user.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto login(UserLoginRequestDto requestDto);
}
