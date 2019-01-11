package com.bespalov.shop.validates;

import com.bespalov.shop.validates.repository.ValidateRepository;
import com.bespalov.shop.validates.repository.ValidatorAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Validator implements ValidateRepository {
    private List<Boolean> booleanList;

    public Validator() {
        booleanList = new ArrayList<>();
    }

    public List<Boolean> getListValidate(Object object) {
        if (Objects.nonNull(object)) {
            Class vClass = object.getClass();
            Field[] fields = vClass.getFields();

            for (Field f : fields) {
                booleanList.add(validateObject(f, object));
            }

            return booleanList;
        }
        return null;
    }

    public Boolean validateObject(Field field, Object object) {
        Annotation[] annotation = field.getAnnotations();
        for (Annotation a : annotation) {
            if (a.annotationType() == ValidatorAnnotation.class) {
                ValidatorAnnotation validator = (ValidatorAnnotation) field.getAnnotation(a.annotationType());
                if (field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    Object obj = field.get(object);
                    Pattern regex = Pattern.compile(validator.value());
                    if (!regex.matcher(obj.toString()).matches()) {
                        System.out.println(validator.errorMessage());
                        return false;
                    } else
                        return true;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }
}
