package devbhdn.photoblock.service.impl;

import devbhdn.photoblock.dto.UserEditUsernameRequestDto;
import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.UserEditProfileRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;
import devbhdn.photoblock.exception.RegistrationException;
import devbhdn.photoblock.exception.UserNotFoundException;
import devbhdn.photoblock.mapper.UserMapper;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.repository.UserRepository;
import devbhdn.photoblock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserResponseDto editProfile(UserEditProfileRequestDto requestDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Can't find user with id: " + id)
        );

        user.setBio(requestDto.bio());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto changeUsername(
            UserEditUsernameRequestDto requestDto,
            Long id
    ) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Can't find user with id: " + id)
        );

        user.setUsername(requestDto.newUsername());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteAccount(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Can't find user with id: " + id)
        );

        userRepository.delete(user);
    }

    @Override
    public UserResponseDto getProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Can't find user with id: " + id)
        );

        return userMapper.toDto(user);
    }
}
