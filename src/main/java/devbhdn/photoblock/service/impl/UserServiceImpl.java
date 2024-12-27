package devbhdn.photoblock.service.impl;

import devbhdn.photoblock.dto.user.UserEditProfileRequestDto;
import devbhdn.photoblock.dto.user.UserEditUsernameRequestDto;
import devbhdn.photoblock.dto.user.UserEditUsernameResponseDto;
import devbhdn.photoblock.dto.user.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.user.UserResponseDto;
import devbhdn.photoblock.exception.RegistrationException;
import devbhdn.photoblock.exception.UserNotFoundException;
import devbhdn.photoblock.mapper.UserMapper;
import devbhdn.photoblock.model.User;
import devbhdn.photoblock.repository.UserRepository;
import devbhdn.photoblock.security.JwtUtil;
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
    private final JwtUtil jwtUtil;

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
        User user = getUserById(id);
        user.setBio(requestDto.bio());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserEditUsernameResponseDto changeUsername(
            UserEditUsernameRequestDto requestDto,
            Long id
    ) {
        User user = getUserById(id);
        String newUsername = requestDto.newUsername();

        user.setUsername(newUsername);
        userRepository.save(user);

        String newToken = jwtUtil.generateToken(newUsername);

        return new UserEditUsernameResponseDto(newUsername, newToken);
    }

    @Override
    public void deleteAccount(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public UserResponseDto getProfile(Long id) {
        User user = getUserById(id);
        return userMapper.toDto(user);
    }

    private User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Can't find user with id: " + id)
        );
    }
}
