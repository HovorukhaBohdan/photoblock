package devbhdn.photoblock.service.impl;

import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.UserRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;
import devbhdn.photoblock.exception.RegistrationException;
import devbhdn.photoblock.exception.UserNotFoundException;
import devbhdn.photoblock.mapper.UserMapper;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.repository.UserRepository;
import devbhdn.photoblock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto) {
        String username = requestDto.username();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RegistrationException(String.format("Username: %s is exists", username));
        }

        User user = userMapper.toEntity(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto editProfile(UserRequestDto requestDto, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Can't find user with username" + username)
        );

        user.setBio(requestDto.bio());

        return userMapper.toDto(userRepository.save(user));
    }
}
