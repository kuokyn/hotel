package com.kuokyn.hotel.service;

import com.kuokyn.hotel.controller.commands.RoomReservationFilter;
import com.kuokyn.hotel.entity.Room;
import com.kuokyn.hotel.entity.RoomReservation;
import com.kuokyn.hotel.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomReservationService {

    List<Room> getAllRooms();

    List<RoomType> getAllRoomTypes();

    Page<RoomReservation> getAllRoomReservations(RoomReservationFilter search, Pageable pageable);

    Page<RoomReservation> getUserRoomReservations(RoomReservationFilter search, Pageable pageable);

    RoomReservation getRoomReservation(Long id);

    void deleteRoomReservation(Long id);

    void saveRoomReservation(RoomReservation roomReservation);
}
