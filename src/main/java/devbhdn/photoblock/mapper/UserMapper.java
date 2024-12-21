package devbhdn.photoblock.mapper;

import devbhdn.photoblock.config.MapperConfig;
import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.UserRequestDto;
import devbhdn.photoblock.dto.UserResponseDto;
import devbhdn.photoblock.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toEntity(UserRegistrationRequestDto requestDto);

    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto requestDto);
}
