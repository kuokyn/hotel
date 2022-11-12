package com.kuokyn.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Getter
@Setter
public class Service {

    @Id
    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    public Service() {
    }
}
