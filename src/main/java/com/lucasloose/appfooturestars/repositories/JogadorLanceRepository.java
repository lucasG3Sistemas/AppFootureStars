package com.lucasloose.appfooturestars.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.JogadorLance;

@Repository
public interface JogadorLanceRepository extends JpaRepository<JogadorLance, Integer> {

	@Transactional(readOnly = true)
	Page<JogadorLance> findByJogadorIn(List<Jogador>jogadores, Pageable pageRequest);
	
	@Transactional(readOnly = true)
	List<JogadorLance> findByJogadorOrderByIdDesc(Jogador jogador);
	
}
