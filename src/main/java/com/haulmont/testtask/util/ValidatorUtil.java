package com.haulmont.testtask.util;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;

public class ValidatorUtil {

    public static Validator<String> getValidatorForString() {
        return (Validator<String>) (val, context) -> {
            if(val.isEmpty() || val.length() == 1)
                return ValidationResult.error("Length of value should be more than 1 char");
            if(val.matches("[^!@#$%^&*()_]"))
                return ValidationResult.error("Value should not have forbidden symbols");
            if(val.length() > 255)
                return ValidationResult.error("Value should not have more 255 chars");
            return ValidationResult.ok();
        };
    }

    public static Validator<String> getValidatorForPhone() {
        return (Validator<String>) (val, context) -> {
            if(val.matches("^(\\+7\\d{10})$")) return ValidationResult.ok();
            else return ValidationResult.error("Phone should be format +79999999999");
        };
    }

    public static String errorNotBeEmpty(){
        return "Should not be empty";
    }

}
