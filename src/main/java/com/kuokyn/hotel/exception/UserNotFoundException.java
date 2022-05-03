package com.kuokyn.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super(String.format("Пользователь не найден"));
    }

    public UserNotFoundException(Long id) {
        super(String.format("Пользователь с id %d не найден", id));
    }
}
