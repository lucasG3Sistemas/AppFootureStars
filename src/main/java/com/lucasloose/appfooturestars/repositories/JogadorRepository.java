package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

	//Spring implementa automaticamente este método
	//readOnly -> não necessita ser envolvida como uma transação de bancos de dados
	@Transactional(readOnly = true)
	Jogador findByEmail(String email);
	
}
