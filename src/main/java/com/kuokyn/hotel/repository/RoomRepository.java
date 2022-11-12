package com.kuokyn.hotel.repository;

import com.kuokyn.hotel.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT distinct v FROM Room v JOIN v.bookings rez WHERE " +
            "(" +
            "v.people >= (:number) AND " +
            "( :startd NOT BETWEEN rez.reservationStartDate AND rez.reservationEndDate ) AND " +
            "( :endd NOT BETWEEN rez.reservationStartDate AND rez.reservationEndDate )" +

            ")"
    )

    Page<Room> findAllRoomsUsingFilter(@Param("number") int p,
                                       @Param("startd") Date s,
                                       @Param("endd") Date e,
                                       Pageable pageable);

    @Query("SELECT distinct v FROM Room v "

    )
    Page<Room> findAllRooms(Pageable pageable);

}
