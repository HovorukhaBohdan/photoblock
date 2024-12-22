package devbhdn.photoblock.service;

import devbhdn.photoblock.dto.UserLoginRequestDto;
import devbhdn.photoblock.dto.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto login(UserLoginRequestDto requestDto);
}
