package com.kuokyn.hotel.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Size(min = 0, max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;

    public User(String login) {
        this.login = login;
    }

    public User() {
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "users_authorities",
            joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authorities_name", referencedColumnName = "name")})

    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    // Реформаттинг к нижнему регистру перед сохранением в бд
    public void setLogin(String login) {
        this.login = login.toLowerCase(Locale.ENGLISH);
    }

}
