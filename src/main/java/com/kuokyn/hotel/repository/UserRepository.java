package com.kuokyn.hotel.repository;

import com.kuokyn.hotel.entity.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT v FROM User v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR " +
            "upper(v.name) LIKE upper(:phrase) OR " +
            "upper(v.surname) LIKE upper(:phrase) OR " +
            "upper(v.phone) LIKE upper(:phrase) OR " +
            "upper(v.email) LIKE upper(:phrase) OR " +
            "upper(v.phone) LIKE upper(:phrase)" +
            ") "

    )
    Page<User> findAllUsersUsingFilter(@Param("phrase") String p, Pageable pageable);

    User findByPhone(String phone);
}
