package com.kuokyn.hotel.service;

import com.kuokyn.hotel.filter.RoomFilter;
import com.kuokyn.hotel.entity.Room;
import com.kuokyn.hotel.entity.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {

    List<RoomType> getAllRoomTypes();

    Room getRoom(Long id);

    void deleteRoom(Long id);

    void saveRoom(Room room);

    Page<Room> getAllRooms(RoomFilter search, Pageable pageable);
    Page<Room> getAllRooms2(Pageable pageable);
}
