package devbhdn.photoblock.dto.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostWithImageLinkResponseDto {
    private Long id;
    private Long userId;
    private String imageLink;
    private String caption;
    private LocalDateTime createdAt;
}
