package com.kuokyn.hotel.service;

import com.kuokyn.hotel.filter.RoomFilter;
import com.kuokyn.hotel.entity.Room;
import com.kuokyn.hotel.entity.RoomType;
import com.kuokyn.hotel.exception.RoomNotFoundException;
import com.kuokyn.hotel.repository.RoomRepository;
import com.kuokyn.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomTypeRepository roomTypeRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomTypeRepository roomTypeRepository, RoomRepository roomRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return null;
    }

    @Override
    public Page<Room> getAllRooms(RoomFilter search, Pageable pageable) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date checkInDate, checkOutDate;
        try {
            checkInDate = formatter.parse(search.getReservationStartDate());
            checkOutDate = formatter.parse(search.getReservationEndDate());

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Page<Room> page;
        page = roomRepository.findAllRoomsUsingFilter(search.getNumberOfPeople(), checkInDate, checkOutDate, pageable);
        return page;
    }

    @Override
    public Page<Room> getAllRooms2(Pageable pageable) {
        Page<Room>  page;
        page = roomRepository.findAllRooms(pageable);
        return page;
    }


    @Override
    public Room getRoom(Long id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Override
    public void deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        } else {
            throw new RoomNotFoundException(id);
        }
    }

    @Override
    public void saveRoom(Room room) {
        roomRepository.save(room);
    }
}
