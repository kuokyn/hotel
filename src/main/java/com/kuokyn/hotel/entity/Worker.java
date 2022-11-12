package com.kuokyn.hotel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "worker")
@Getter
@Setter
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 128, nullable = false)
    @Email
    private String email;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "surname", length = 128, nullable = false)
    private String surname;

    @Size(max = 12)
    @Column(name = "phone")
    private String phone;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_title", nullable = false)
    private Job job;

    public Worker() {
    }
}
