package com.vinehds.dailysinc.repository;

import com.vinehds.dailysinc.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    UserDetails findUserDetailsById(Long id);

}