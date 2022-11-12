package com.kuokyn.hotel.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "people")
    private Integer numberOfPeople;

    @Column(name = "check_in_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationStartDate;

    @Column(name = "check_out_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservationEndDate;

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public Booking(@Valid Room room, @Valid User user, int numberOfPeople, String reservationStartDate, String reservationEndDate) {
        this.room = room;
        this.user = user;
        this.numberOfPeople = numberOfPeople;
        try {
            this.reservationStartDate = formatter.parse(reservationStartDate);
            this.reservationEndDate = formatter.parse(reservationEndDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
//        this.reservationStartDate = reservationStartDate;
//        this.reservationEndDate = reservationEndDate;
    }

    public Booking() {
        this.user = new User();
        this.room = new Room();
    }

}
