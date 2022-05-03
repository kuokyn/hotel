package com.kuokyn.hotel.annotations;


import com.kuokyn.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired(required = false)
    private UserService userService;

    public void initialize(UniqueUsername constraint) {
    }

    public boolean isValid(String login, ConstraintValidatorContext context) {
        //w trakcie uruchamiania RepositoryInitializer usługi userService jeszcze nie ma wstrzykniętej do tego walidatora dlatego userService == null.
        return userService == null || (login != null && userService.isUniqueLogin(login));
    }

}