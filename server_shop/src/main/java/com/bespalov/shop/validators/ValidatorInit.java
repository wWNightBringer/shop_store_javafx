package com.bespalov.shop.validators;

import com.bespalov.shop.repository.ValidatorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorInit {
    private List<ValidatorRepository> list;
    private List<String> stringList;
    private List<Boolean> booleanList;

    public ValidatorInit() {
        list = new ArrayList<>();
        stringList = new ArrayList<>();
        list.add(new Validator());
    }

    public Boolean showValitationResult(Object object) {
        booleanList = new ArrayList<>();

        for (ValidatorRepository repository : list) {
            stringList.addAll(repository.validation(object));
        }
        stringList.forEach(System.out::println);
        for (String s : stringList) {
            if (!s.equalsIgnoreCase("Success")) {
                booleanList.add(Boolean.FALSE);
                continue;
            }
            booleanList.add(Boolean.TRUE);
        }
        stringList.clear();
        if (booleanList.contains(Boolean.FALSE))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

}
