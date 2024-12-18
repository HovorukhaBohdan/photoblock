package devbhdn.photoblock.dto;

public record UserRegistrationRequestDto(
        String username,
        String password,
        String repeatPassword,
        String bio
    ) {
}
