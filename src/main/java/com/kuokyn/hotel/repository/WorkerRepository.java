package com.kuokyn.hotel.repository;

import com.kuokyn.hotel.entity.User;
import com.kuokyn.hotel.entity.Worker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query("SELECT v FROM Worker v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR " +
            "upper(v.name) LIKE upper(:phrase) OR " +
            "upper(v.surname) LIKE upper(:phrase) OR " +
            "upper(v.phone) LIKE upper(:phrase) OR " +
            "upper(v.email) LIKE upper(:phrase) " +
            ") "

    )
    Page<Worker> findAllWorkersUsingFilter(@Param("phrase") String p, Pageable pageable);

}
