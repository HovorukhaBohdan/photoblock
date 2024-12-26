package devbhdn.photoblock.mapper;

import devbhdn.photoblock.config.MapperConfig;
import devbhdn.photoblock.dto.user.UserRegistrationRequestDto;
import devbhdn.photoblock.dto.user.UserResponseDto;
import devbhdn.photoblock.model.Post;
import devbhdn.photoblock.model.User;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

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
