package com.lucasloose.appfooturestars.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.ListaObservacao;

@Repository
public interface ListaObservacaoRepository extends JpaRepository<ListaObservacao, Integer> {
	
//	SELECT id FROM ListaObservacao obj LEFT JOIN lista_obs_jogador ON id_lista_observacao=id where id=1 and id_jogador in (1)
	@Query("SELECT obj.id FROM ListaObservacao obj LEFT JOIN obj.jogadores jog WHERE obj.id= :id AND jog IN :jogadores")
	List<ListaObservacao> findByIdAndJogadoresIn(Integer id, List<Jogador> jogadores);
	
	@Transactional(readOnly = true)
	ListaObservacao findByClubeFutebol(ClubeFutebol clubeFutebol);
	
	@Transactional(readOnly = true)
	ListaObservacao findByEmpresario(Empresario empresario);
	
//	List<ListaObservacao> removeByIdAndJogadoresIn(Integer id, List<Jogador> jogadores);
	
}
