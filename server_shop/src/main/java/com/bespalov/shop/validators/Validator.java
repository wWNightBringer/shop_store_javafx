package com.bespalov.shop.validators;

import com.bespalov.shop.annotation.ValidatorAnnotation;
import com.bespalov.shop.repository.ValidatorRepository;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Validator implements ValidatorRepository {
    private List<String> list;

    @Override
    public List validation(Object object) {
        list = new ArrayList<>();
        if (Objects.nonNull(object)) {
            Class vClass = object.getClass();
            Field[] fields = vClass.getDeclaredFields();
            for (Field field : fields) {
                String s = validationEngine(field, object);
                if (s != null)
                    list.add(s);

            }
            return list;
        }

        return null;
    }

    @Override
    public String validationEngine(Field field, Object object) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == ValidatorAnnotation.class) {
                ValidatorAnnotation validatorAnnotation = (ValidatorAnnotation) field.getAnnotation(annotation.annotationType());
                if (field.isAccessible()) {
                    field.setAccessible(true);
                }
                try {
                    Object obj = field.get(object);
                    Pattern regex = Pattern.compile(validatorAnnotation.formInputData());
                    if (!regex.matcher((CharSequence) obj).matches()) {
                        return validatorAnnotation.errorMessage();
                    }
                    return "Success";
                } catch (IllegalAccessException e) {
                    e.printStackTrace();

                }
            }


        }

        return null;
    }

}
