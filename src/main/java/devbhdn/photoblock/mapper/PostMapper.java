package devbhdn.photoblock.mapper;

import devbhdn.photoblock.config.MapperConfig;
import devbhdn.photoblock.dto.post.PostResponseDto;
import devbhdn.photoblock.dto.post.PostWithImageLinkResponseDto;
import devbhdn.photoblock.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class)
public interface PostMapper {
    @Mapping(target = "userId", source = "user.id")
    PostResponseDto toDto(Post post);

    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "imageLink", ignore = true)
    })
    PostWithImageLinkResponseDto toDtoWithImageLink(Post post);
}
