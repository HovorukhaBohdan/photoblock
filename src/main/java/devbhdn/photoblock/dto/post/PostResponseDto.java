package devbhdn.photoblock.dto.post;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        Long userId,
        String imageLink,
        String caption,
        LocalDateTime createdAt
) {
}
