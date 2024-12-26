package devbhdn.photoblock.mapper;

import devbhdn.photoblock.config.MapperConfig;
import devbhdn.photoblock.dto.user.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.user.UserResponseDto;
import devbhdn.photoblock.model.Post;
import devbhdn.photoblock.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    User toEntity(UserRegistrationRequestDto requestDto);

    @Mapping(target = "postsIds", ignore = true)
    UserResponseDto toDto(User user);

    @AfterMapping
    default void setPostsIds(@MappingTarget UserResponseDto responseDto, User user) {
        List<Long> postsIds = user.getPosts().stream()
                .map(Post::getId)
                .toList();

        responseDto.setPostsIds(postsIds);
    }
}
