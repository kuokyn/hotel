package com.kuokyn.hotel.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room_types")
@Getter
@Setter
public class RoomType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String roomTypeName;

    @Column(name = "min_price", nullable = false)
    private int minPrice;

    @Column(name = "max_price", nullable = false)
    private int maxPrice;


    public RoomType() {

    }

    public RoomType(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    @Override
    public String toString() {
        return "roomTypeName";
    }
}
