package com.vinehds.dailysinc.repository;

import com.vinehds.dailysinc.model.entitie.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
