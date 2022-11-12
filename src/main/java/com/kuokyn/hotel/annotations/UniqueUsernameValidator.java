package com.kuokyn.hotel.annotations;


import com.kuokyn.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired(required = false)
    private UserService userService;

    public void initialize(UniqueUsername constraint) {
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return phone != null && userService.isUniquePhone(phone);
    }

}