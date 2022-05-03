package com.kuokyn.hotel.repository;

import com.kuokyn.hotel.entity.RoomReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomReservationRepository
       extends JpaRepository<RoomReservation, Long>, JpaSpecificationExecutor<RoomReservation>
{
    //nazwa metody jest jednocześnie zapytaniem
    //Page<RoomReservation> findByNameContaining(String phrase, Pageable pageable);

    //nad klasą Vehicle znajduje się definicja zapytania (@NamedQuery) powiązana z tą metodą
  //  Page<RoomReservation> findAllRoomReservationsUsingNamedQuery(String phrase, Pageable pageable);

    @Query("SELECT v FROM RoomReservation v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(v.user.firstName) LIKE upper(:phrase) OR " +
            "upper(v.user.lastName) LIKE upper(:phrase) OR " +
            "upper(v.user.login) LIKE upper(:phrase) )"


    )
    Page<RoomReservation> findAllRoomReservationsUsingFilter(@Param("phrase") String p, Pageable pageable);

    @Query("SELECT v FROM RoomReservation v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "v.user.login LIKE (:phrase) )"

    )
    Page<RoomReservation> findUserRoomReservations(@Param("phrase") String p, Pageable pageable);


}
