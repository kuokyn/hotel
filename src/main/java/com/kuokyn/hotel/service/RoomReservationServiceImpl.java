package com.kuokyn.hotel.service;

import com.kuokyn.hotel.entity.Booking;
import com.kuokyn.hotel.filter.RoomReservationFilter;
import com.kuokyn.hotel.entity.Room;
import com.kuokyn.hotel.entity.RoomType;
import com.kuokyn.hotel.exception.RoomReservationNotFoundException;
import com.kuokyn.hotel.repository.RoomRepository;
import com.kuokyn.hotel.repository.RoomReservationRepository;
import com.kuokyn.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

    private final RoomTypeRepository roomTypeRepository;

    private final RoomRepository roomRepository;

    private final RoomReservationRepository roomReservationRepository;

    @Autowired
    public RoomReservationServiceImpl(RoomTypeRepository roomTypeRepository, RoomRepository roomRepository, RoomReservationRepository roomReservationRepository) {
        this.roomTypeRepository = roomTypeRepository;
        this.roomRepository = roomRepository;
        this.roomReservationRepository = roomReservationRepository;
    }


    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return roomTypeRepository.findAll();
    }

    @Override
    public Page<Booking> getAllRoomReservations(RoomReservationFilter search, Pageable pageable) {

        Page<Booking> page;
        if (search.isEmpty()) {
            page = roomReservationRepository.findAll(pageable);
        } else {
            page = roomReservationRepository.findAllRoomReservationsUsingFilter(search.getPhraseLIKE(), pageable);
        }
        return page;
    }

    @Override
    public Page<Booking> getUserRoomReservations(RoomReservationFilter search, Pageable pageable) {
        Page<Booking> page;
        if (search.isEmpty()) {
            page = roomReservationRepository.findAll(pageable);
        } else {
            page = roomReservationRepository.findUserRoomReservations(search.getPhraseLIKE(), pageable);
        }
        return page;
    }

    @Transactional
    @Override
    public Booking getRoomReservation(Long id) {
        Optional<Booking> optionalRoomReservation = roomReservationRepository.findById(id);
        return optionalRoomReservation.orElseThrow(() -> new RoomReservationNotFoundException(id));


    }

    @Override
    public void deleteRoomReservation(Long id) {
        if (roomReservationRepository.existsById(id)) {
            roomReservationRepository.deleteById(id);
        } else {
            throw new RoomReservationNotFoundException(id);
        }

    }

    @Override
    public void saveRoomReservation(Booking booking) {

       /* List<RoomReservation> found = roomReservationRepository.findRoomReservationById(roomReservation.getRoom().getId());

        for (RoomReservation roomReservation1 : found) {
            if (roomReservation.getReservationStartDate().after(roomReservation1.getReservationEndDate())) {
                roomReservationRepository.save(roomReservation);
            }

        }*/
        roomReservationRepository.save(booking);
    }

    public List<Booking> getRoomReservationsByUserId(Long id) {
        return roomReservationRepository.findRoomReservationByUserId(id);
    }

}
