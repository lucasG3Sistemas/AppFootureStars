package com.lucasloose.appfooturestars.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

	//Spring implementa automaticamente este método
	//readOnly -> não necessita ser envolvida como uma transação de bancos de dados
	@Transactional(readOnly = true)
	Jogador findByEmail(String email);
	
//	@Query("SELECT DISTINCT obj FROM Jogador obj INNER JOIN obj.posicoes pos WHERE obj.nome LIKE %:nome% AND pos IN :posicoes")
//	Page<Jogador> search(@Param("nome") String nome, @Param("posicoes") List<ModalidadePosicao>posicoes, Pageable pageRequest);
	
	@Transactional(readOnly = true)
	Page<Jogador> findDistinctByNomeContainingAndPosicoesIn(String nome, List<ModalidadePosicao>posicoes, Pageable pageRequest);
	
}
