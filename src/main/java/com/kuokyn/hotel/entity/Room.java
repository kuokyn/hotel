package com.kuokyn.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

@Entity
@Table(name = "room")
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_title", nullable = false)
    private RoomType roomType;

    @Column(name="chambers", nullable = false)
    private int chambers;

    @Column(name="people", nullable = false)
    private int people;

    @OneToMany(mappedBy = "room")
    private Set<Booking> bookings;

    public Room() {
        this.roomType = new RoomType();
    }

}
