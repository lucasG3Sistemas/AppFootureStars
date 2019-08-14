package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.ListaObservacao;

@Repository
public interface ListaObservacaoRepository extends JpaRepository<ListaObservacao, Integer> {

}
