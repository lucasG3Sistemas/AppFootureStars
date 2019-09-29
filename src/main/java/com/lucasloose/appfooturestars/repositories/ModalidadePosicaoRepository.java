package com.lucasloose.appfooturestars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;

@Repository
public interface ModalidadePosicaoRepository extends JpaRepository<ModalidadePosicao, Integer> {
	
	@Transactional(readOnly = true)
	List<ModalidadePosicao> findByModalidadesIn(List<Modalidade>modalidades);
	
}
