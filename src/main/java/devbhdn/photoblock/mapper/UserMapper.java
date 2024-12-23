package devbhdn.photoblock.mapper;

import devbhdn.photoblock.config.MapperConfig;
import devbhdn.photoblock.dto.user.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.user.UserResponseDto;
import devbhdn.photoblock.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toEntity(UserRegistrationRequestDto requestDto);

    UserResponseDto toDto(User user);
}
