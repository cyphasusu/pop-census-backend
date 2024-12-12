package com.ecl.popcensus.util.validators;


import com.ecl.popcensus.util.ValidationUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements
        ConstraintValidator<NameConstraint, String> {

    @Override
    public void initialize(NameConstraint phoneNumberConstraint) {
    }

    @Override
    public boolean isValid(String name,
                           ConstraintValidatorContext cxt) {
        return ValidationUtil.nameIsValid(name);
    }

}