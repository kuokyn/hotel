package com.kuokyn.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such reservation")
public class RoomReservationNotFoundException extends RuntimeException {

    public RoomReservationNotFoundException() {
        super("Бронирование не найдено");
    }

    public RoomReservationNotFoundException(Long id) {
        super(String.format("Бронирование с id '%d' не найдено", id));
    }
}
