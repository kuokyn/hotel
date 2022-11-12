package com.kuokyn.hotel.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "room_type")
@Getter
@Setter
public class RoomType implements Serializable {

    @Id
    @Column(name = "title", length=20, nullable = false)
    private String id;

    @Column(name = "single_beds", nullable = false)
    private int single_beds;

    @Column(name = "double_beds", nullable = false)
    private int double_beds;

    @Column(name = "price", nullable = false)
    private double price;

    public RoomType() {
    }

    @Override
    public String toString() {
        return "roomTypeName";
    }
}
