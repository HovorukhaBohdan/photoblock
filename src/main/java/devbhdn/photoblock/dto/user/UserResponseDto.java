package devbhdn.photoblock.dto.user;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String bio;
    private List<Long> postsIds;
}
