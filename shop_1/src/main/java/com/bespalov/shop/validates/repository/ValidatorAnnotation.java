package com.bespalov.shop.validates.repository;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface ValidatorAnnotation {
    String value() default "";
    String errorMessage() default "";
}
