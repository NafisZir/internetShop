package com.example.myShop.validation.annotation;

import com.example.myShop.validation.validator.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author nafis
 * @since 29.12.2021
 */

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface Phone {
    String message() default "{user.phone.notValid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
