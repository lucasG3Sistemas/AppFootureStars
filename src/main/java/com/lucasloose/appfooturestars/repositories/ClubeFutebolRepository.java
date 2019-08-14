package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;

@Repository
public interface ClubeFutebolRepository extends JpaRepository<ClubeFutebol, Integer> {

}
