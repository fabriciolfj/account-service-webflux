package com.github.fabriciolfj.accountservice.domain.exceptions.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ValidateFormatDateImpl implements ConstraintValidator<ValidateFormatDate, String> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return false;
        }

        try {
            LocalDate.parse(value, DATE_FORMAT);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
