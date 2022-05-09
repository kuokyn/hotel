package com.kuokyn.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Date;

@Entity
@Table(name = "room_reservations")
@Getter
@Setter
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rooms_id", nullable = false)
    private Room room;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @Column(name = "number_of_people")
    private Integer numberOfPeople;

    @Column(name = "reservation_start_date", nullable = false)
    private Date reservationStartDate;

    @Column(name = "reservation_end_date", nullable = false)
    private Date reservationEndDate;

    public RoomReservation(@Valid Room room, @Valid User user ,int numberOfPeople, Date reservationStartDate, Date reservationEndDate) {
        this.room = room;
        this.user = user;
        this.numberOfPeople = numberOfPeople;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
    }

    public RoomReservation() {
        this.user = new User();
        this.room = new Room();
    }

}
