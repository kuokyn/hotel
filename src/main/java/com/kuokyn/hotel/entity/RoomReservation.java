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
    @ManyToOne(fetch = FetchType.EAGER)//
    @JoinColumn(name = "rooms_id", nullable = false)
    private Room room;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)//
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @Column(name = "end_price", nullable = false)
    private int endPrice;

    @Column(name = "check_in_date")
    private Date checkInDate;

    @Column(name = "check_out_date")
    private Date checkOutDate;

    @Column(name = "number_of_people", nullable = false)
    private int numberOfPeople;

    @Column(name = "reservation_start_date", nullable = false)
    private Date reservationStartDate;

    @Column(name = "reservation_end_date", nullable = false)
    private Date reservationEndDate;

    @Column
    private boolean verified;

    @Column
    private boolean paid;

    public RoomReservation(@Valid Room room, @Valid User user, int endPrice, Date checkInDate, Date checkOutDate, int numberOfPeople, Date reservationStartDate, Date reservationEndDate, boolean verified, boolean paid) {
        this.room = room;
        this.user = user;
        this.endPrice = endPrice;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfPeople = numberOfPeople;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.verified = verified;
        this.paid = paid;
    }

    public RoomReservation(@Valid Room room, @Valid User user, int endPrice, Date checkInDate, Date checkOutDate, int numberOfPeople, Date reservationStartDate, Date reservationEndDate) {
        this.room = room;
        this.user = user;
        this.endPrice = endPrice;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfPeople = numberOfPeople;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
    }

    public RoomReservation() {
        this.user = new User();
        this.room = new Room();
    }

}
