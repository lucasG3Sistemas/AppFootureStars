package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.JogadorLance;

@Repository
public interface JogadorLanceRepository extends JpaRepository<JogadorLance, Integer> {

}
