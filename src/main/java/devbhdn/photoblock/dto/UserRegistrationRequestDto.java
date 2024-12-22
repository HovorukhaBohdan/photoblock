package devbhdn.photoblock.dto;

import devbhdn.photoblock.validation.PasswordMatch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@PasswordMatch
public record UserRegistrationRequestDto(
        @NotBlank(message = NOT_BLANK_FIELD)
        String username,
        @Length(min = 8, message = PASSWORD_FIELD)
        String password,
        @Length(min = 8, message = PASSWORD_FIELD)
        String repeatPassword,
        @NotNull(message = NOT_NULL_FIELD)
        String bio
) {
    public static final String NOT_BLANK_FIELD = "Field can't be empty or null";
    public static final String PASSWORD_FIELD = "Password length must be 8 characters or more";
    public static final String NOT_NULL_FIELD = "Field can't be null";
}
