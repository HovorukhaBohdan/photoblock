package devbhdn.photoblock.validation;

import devbhdn.photoblock.dto.UserLoginRequestDto;
import devbhdn.photoblock.dto.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PasswordMatchValidator
        implements ConstraintValidator<PasswordMatch, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(
            UserRegistrationRequestDto requestDto,
            ConstraintValidatorContext constraintValidatorContext
    ) {
        return Objects.equals(requestDto.password(), requestDto.repeatPassword());
    }
}
