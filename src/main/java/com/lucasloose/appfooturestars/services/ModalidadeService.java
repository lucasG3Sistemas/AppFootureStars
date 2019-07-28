package com.lucasloose.appfooturestars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;

@Service
public class ModalidadeService {

	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	public Modalidade buscar(Integer id) {
		Modalidade modalidade = modalidadeRepository.findOne(id);
		return modalidade;
	}
	
}
