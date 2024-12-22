package devbhdn.photoblock.dto;

import jakarta.validation.constraints.NotNull;

public record UserEditProfileRequestDto(
        @NotNull(message = NOT_NULL_FIELD)
        String bio
) {
    public static final String NOT_NULL_FIELD = "Field can't be null";
}
