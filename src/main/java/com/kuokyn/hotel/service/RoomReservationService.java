package com.kuokyn.hotel.service;

import com.kuokyn.hotel.entity.Booking;
import com.kuokyn.hotel.filter.RoomReservationFilter;
import com.kuokyn.hotel.entity.Room;
import com.kuokyn.hotel.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomReservationService {

    List<Room> getAllRooms();

    List<RoomType> getAllRoomTypes();

    Page<Booking> getAllRoomReservations(RoomReservationFilter search, Pageable pageable);

    Page<Booking> getUserRoomReservations(RoomReservationFilter search, Pageable pageable);

    Booking getRoomReservation(Long id);

    void deleteRoomReservation(Long id);

    void saveRoomReservation(Booking booking);
}
