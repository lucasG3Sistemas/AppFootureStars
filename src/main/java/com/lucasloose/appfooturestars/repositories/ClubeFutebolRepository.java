package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;

@Repository
public interface ClubeFutebolRepository extends JpaRepository<ClubeFutebol, Integer> {
	
	//Spring implementa automaticamente este método
	//readOnly -> não necessita ser envolvida como uma transação de bancos de dados
	@Transactional(readOnly = true)
	ClubeFutebol findByEmail(String email);
	
}
