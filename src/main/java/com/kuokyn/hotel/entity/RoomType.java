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

    public Long getId() {
        return id;
    }

    @Column(name="name",nullable = false)
    private String roomTypeName;
    @Column(name = "min_price", nullable = false)
    private int minPrice;
    @Column(name = "max_price", nullable = false)
    private int maxPrice;

}
