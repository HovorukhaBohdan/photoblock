package devbhdn.photoblock.dto.user;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String bio;
    private List<Long> postsIds;
}
