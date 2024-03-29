package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.HistoricoContratacao;

@Repository
public interface HistoricoContratacaoRepository extends JpaRepository<HistoricoContratacao, Integer> {

}
