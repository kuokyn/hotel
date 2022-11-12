package com.kuokyn.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such reservation")
public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException() {
        super("Комната не найдена");
    }

    public RoomNotFoundException(Long id) {
        super(String.format("Комната с id '%d' не найдена", id));
    }
}
