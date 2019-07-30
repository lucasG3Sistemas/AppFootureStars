package com.lucasloose.appfooturestars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ModalidadeService {

	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	public Modalidade buscar(Integer id) {
		Modalidade modalidade = modalidadeRepository.findOne(id);
		if (modalidade == null) {
			throw new ObjectNotFoundException("Modalidade não encontrada! ID: " + id
					+ ", Tipo: " + Modalidade.class.getName());
		}
		return modalidade;
	}
	
}
