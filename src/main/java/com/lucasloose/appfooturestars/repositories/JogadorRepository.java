package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

}
