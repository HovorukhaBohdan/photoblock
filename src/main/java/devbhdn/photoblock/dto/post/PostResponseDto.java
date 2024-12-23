package devbhdn.photoblock.dto.post;

import java.time.LocalDateTime;

public record PostResponseDto(
        Long id,
        Long userId,
        byte[] image,
        String caption,
        LocalDateTime createdAt
) {
}
