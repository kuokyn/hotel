package com.kuokyn.hotel.repository;

import com.kuokyn.hotel.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomReservationRepository
        extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    @Query("SELECT v FROM Booking v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR " +
            "upper(v.user.name) LIKE upper(:phrase) OR " +
            "upper(v.user.surname) LIKE upper(:phrase) OR " +
            "upper(v.user.phone) LIKE upper(:phrase) )"


    )
    Page<Booking> findAllRoomReservationsUsingFilter(@Param("phrase") String p, Pageable pageable);

    @Query("SELECT v FROM Booking v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR " +
            "v.user.phone LIKE (:phrase) )"

    )
    Page<Booking> findUserRoomReservations(@Param("phrase") String p, Pageable pageable);

    List<Booking> findRoomReservationById(Long id);

    List<Booking> findRoomReservationByUserId(Long id);

    void deleteAllByUserId(Long id);
}
