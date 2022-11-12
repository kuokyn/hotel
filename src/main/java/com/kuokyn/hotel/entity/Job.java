package com.kuokyn.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job")
@Getter
@Setter
public class Job {
    @Id
    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    public Job() {
    }

    public Job(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
