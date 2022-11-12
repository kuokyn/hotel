package com.kuokyn.hotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password", nullable = false)
    private String password;

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

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_phone", referencedColumnName = "phone")},
            inverseJoinColumns = {@JoinColumn(name = "authority_title", referencedColumnName = "title")})
    private Set<Authority> authorities = new HashSet<>();

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Authority> authList;
        authList = this.authorities;
        return (Collection<? extends GrantedAuthority>) authList;
    }

    @Override
    public String getUsername() {
        return this.phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
