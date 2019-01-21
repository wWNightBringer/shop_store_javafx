package com.bespalov.shop.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ ElementType.METHOD,ElementType.FIELD})
public @interface ValidatorAnnotation {
    String formInputData() default "";

    String errorMessage() default "Incorrect data";
}
