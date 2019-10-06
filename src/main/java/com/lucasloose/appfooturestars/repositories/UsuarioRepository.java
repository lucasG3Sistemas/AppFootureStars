package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	@Transactional(readOnly = true)
	Usuario findByEmail(String email);
	
}
