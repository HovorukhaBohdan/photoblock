package devbhdn.photoblock.service.impl;

import devbhdn.photoblock.dto.UserLoginRequestDto;
import devbhdn.photoblock.dto.UserLoginResponseDto;
import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;
import devbhdn.photoblock.mapper.UserMapper;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.repository.UserRepository;
import devbhdn.photoblock.security.JwtUtil;
import devbhdn.photoblock.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.username(),
                        requestDto.password()
                )
        );

        return new UserLoginResponseDto(jwtUtil.generateToken(authentication.getName()));
    }
}
