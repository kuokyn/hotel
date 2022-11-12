package com.kuokyn.hotel.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "authority")
@Getter
@Setter
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    public Authority() {
    }

    public Authority(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        return Objects.equals(title, ((Authority) o).title);
    }

    @Override
    public String getAuthority() {
        return this.title;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "title='" + title + '\'' +
                '}';
    }
}
