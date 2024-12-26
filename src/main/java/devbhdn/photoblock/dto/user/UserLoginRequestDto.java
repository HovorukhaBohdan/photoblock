package devbhdn.photoblock.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserLoginRequestDto(
        @NotBlank(message = NOT_BLANK_FIELD)
        String username,
        @Length(min = 8, message = PASSWORD_FIELD)
        String password
) {
    public static final String NOT_BLANK_FIELD = "Field can't be empty or null";
    public static final String PASSWORD_FIELD = "Password length must be 8 characters or more";
}
