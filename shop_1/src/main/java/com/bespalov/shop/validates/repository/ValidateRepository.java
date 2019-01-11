package com.bespalov.shop.validates.repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface ValidateRepository extends Serializable {
    List<Boolean> getListValidate(Object object);

    Boolean validateObject(Field field, Object object);
}
