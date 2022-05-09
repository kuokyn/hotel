package com.kuokyn.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;


@Entity
@Table(name = "rooms")
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_number_of_people", nullable = false)
    private int maxNumberOfPeople;

    @Column(name = "room_number", nullable = false)
    private int roomNumber;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private int price;

    @Column(name = "double_beds", nullable = false)
    private int doubleBeds;

    @Column(name = "single_beds", nullable = false)
    private int singleBeds;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "types_id", nullable = false)
    private RoomType roomType;


    @OneToMany(mappedBy = "room")
    private Set<RoomReservation> roomReservations;

    public Room(int maxNumberOfPeople, int roomNumber, int floor, int price, int doubleBeds, int singleBeds, @Valid RoomType roomType) {
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.price = price;
        this.doubleBeds = doubleBeds;
        this.singleBeds = singleBeds;
        this.roomType = roomType;
    }

    public Room() {
        this.roomType = new RoomType();
    }

}
