package com.bespalov.shop.repository;

import java.lang.reflect.Field;
import java.util.List;

public interface ValidatorRepository {
    List validation(Object object);

    String validationEngine(Field field, Object object);
}
