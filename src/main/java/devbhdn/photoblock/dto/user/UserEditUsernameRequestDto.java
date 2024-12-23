package devbhdn.photoblock.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserEditUsernameRequestDto(
        @NotBlank(message = NOT_BLANK_USERNAME)
        String newUsername
) {
    public static final String NOT_BLANK_USERNAME = "Username can't be empty or null";
}
