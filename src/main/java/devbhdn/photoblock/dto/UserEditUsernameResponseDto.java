package devbhdn.photoblock.dto;

public record UserEditUsernameResponseDto(
    String newUsername,
    String newToken
) {
}
