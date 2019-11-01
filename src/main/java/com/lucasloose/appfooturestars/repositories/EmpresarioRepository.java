package com.lucasloose.appfooturestars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Usuario;

@Repository
public interface EmpresarioRepository extends JpaRepository<Empresario, Integer> {
	
	//Spring implementa automaticamente este método
	//readOnly -> não necessita ser envolvida como uma transação de bancos de dados
//	@Transactional(readOnly = true)
//	Empresario findByEmail(String email);
	
	@Transactional(readOnly = true)
	Empresario findByUsuario(Usuario usuario);
	
}