package com.github.fabriciolfj.accountservice.domain.exceptions.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValidateFormatDateImpl.class})
public @interface ValidateFormatDate {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
