package devbhdn.photoblock.dto.user;

public record UserEditUsernameResponseDto(
        String newUsername,
        String newToken
) {
}
