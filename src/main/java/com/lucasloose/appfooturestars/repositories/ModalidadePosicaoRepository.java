package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.ModalidadePosicao;

@Repository
public interface ModalidadePosicaoRepository extends JpaRepository<ModalidadePosicao, Integer> {

}
