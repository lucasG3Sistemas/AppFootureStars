package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasloose.appfooturestars.domain.Empresario;

@Repository
public interface EmpresarioRepository extends JpaRepository<Empresario, Integer> {

}
