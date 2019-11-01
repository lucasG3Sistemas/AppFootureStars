package com.lucasloose.appfooturestars.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.domain.Usuario;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

	//Spring implementa automaticamente este método
	//readOnly -> não necessita ser envolvida como uma transação de bancos de dados
	@Transactional(readOnly = true)
	Jogador findByEmail(String email);
	
	@Transactional(readOnly = true)
	Jogador findByUsuario(Usuario usuario);
	
	@Transactional(readOnly = true)
	List<Jogador> findByClubeFutebol(ClubeFutebol clubeFutebol);
	
	@Transactional(readOnly = true)
	List<Jogador> findByEmpresario(Empresario empresario);
	
//	@Query("SELECT DISTINCT obj FROM Jogador obj INNER JOIN obj.posicoes pos WHERE obj.nome LIKE %:nome% AND pos IN :posicoes")
//	Page<Jogador> search(@Param("nome") String nome, @Param("posicoes") List<ModalidadePosicao>posicoes, Pageable pageRequest);
	
	@Transactional(readOnly = true)
	Page<Jogador> findDistinctByNomeContainingAndPosicoesIn(String nome, List<ModalidadePosicao>posicoes, Pageable pageRequest);
		
	@Query(					
			value = "SELECT DISTINCT JOGADOR.* FROM JOGADOR WHERE NOT EXISTS (SELECT * FROM LISTA_OBS_JOGADOR WHERE ID_LISTA_OBSERVACAO=:idLista AND ID_JOGADOR=ID) AND (ID_EMPRESARIO<>:idEmpresario OR ID_EMPRESARIO IS NULL)",
			nativeQuery = true)
	List<Jogador> listaJogadoresEmpresario(@Param("idLista") Integer idLista, @Param("idEmpresario") Integer idEmpresario);
	
	@Query(					
			value = "SELECT DISTINCT JOGADOR.* FROM JOGADOR WHERE (ID_EMPRESARIO<>:idEmpresario OR ID_EMPRESARIO IS NULL)",
			nativeQuery = true)
	List<Jogador> listaJogadoresEmpresario(@Param("idEmpresario") Integer idEmpresario);
	
	@Query(					
			value = "SELECT DISTINCT JOGADOR.* FROM JOGADOR WHERE NOT EXISTS (SELECT * FROM LISTA_OBS_JOGADOR WHERE ID_LISTA_OBSERVACAO=:idLista AND ID_JOGADOR=ID) AND (ID_CLUBE_FUTEBOL<>:idClubeFutebol OR ID_CLUBE_FUTEBOL IS NULL)",
			nativeQuery = true)
	List<Jogador> listaJogadoresClubeFutebol(@Param("idLista") Integer idLista, @Param("idClubeFutebol") Integer idClubeFutebol);
	
	@Query(					
			value = "SELECT DISTINCT JOGADOR.* FROM JOGADOR WHERE (ID_CLUBE_FUTEBOL<>:idClubeFutebol OR ID_CLUBE_FUTEBOL IS NULL)",
			nativeQuery = true)
	List<Jogador> listaJogadoresClubeFutebol(@Param("idClubeFutebol") Integer idClubeFutebol);
	
}
