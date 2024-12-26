package devbhdn.photoblock.dto.post;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PostWithImageLinkResponseDto {
    private Long id;
    private Long userId;
    private String imageLink;
    private String caption;
    private LocalDateTime createdAt;
}
