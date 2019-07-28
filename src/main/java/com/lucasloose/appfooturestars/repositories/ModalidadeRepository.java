package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.Modalidade;

@Repository
public interface ModalidadeRepository extends JpaRepository<Modalidade, Integer> {

}
