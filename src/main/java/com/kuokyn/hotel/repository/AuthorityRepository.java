package com.kuokyn.hotel.repository;

import com.kuokyn.hotel.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AuthorityRepository extends JpaRepository<Authority,String> {
    Authority findAuthorityByTitle(String title);

}
