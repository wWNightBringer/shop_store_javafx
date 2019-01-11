package com.bespalov.shop.validates;

import com.bespalov.shop.validates.repository.ValidateRepository;

import java.util.ArrayList;
import java.util.List;

public class ListValidator {
    private List<ValidateRepository> list;

    public ListValidator() {
        list = new ArrayList<>();
        list.add(new Validator());
    }

    public Boolean getResult(Object object) {
        for (ValidateRepository repository : list) {
            if (repository.getListValidate(object).contains(Boolean.FALSE)) {
                return false;
            }
        }
        return true;
    }
}
