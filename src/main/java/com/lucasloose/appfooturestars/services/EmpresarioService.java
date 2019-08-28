package com.lucasloose.appfooturestars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class EmpresarioService {

	@Autowired
	private EmpresarioRepository empresarioRepository;
	
	
	public Empresario find(Integer id) {
		Empresario empresario = empresarioRepository.findOne(id);
		if (empresario == null) {
			throw new ObjectNotFoundException("Empresário não encontrado! ID: " + id
					+ ", Tipo: " + Empresario.class.getName());
		}
		return empresario;
	}
	
}
