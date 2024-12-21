package devbhdn.photoblock.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotNull(message = NOT_NULL_FIELD)
        String bio
) {
    public static final String NOT_NULL_FIELD = "Field can't be null";
}
