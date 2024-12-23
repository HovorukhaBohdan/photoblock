package devbhdn.photoblock.mapper;

import devbhdn.photoblock.config.MapperConfig;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PostMapper {
    @Mapping(target = "userId", source = "user.id")
    PostResponseDto toDto(Post post);
}
