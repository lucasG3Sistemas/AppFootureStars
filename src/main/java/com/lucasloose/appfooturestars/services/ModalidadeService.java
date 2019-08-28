package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ModalidadeService {

	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	public List<Modalidade> findAll() {
		List<Modalidade> modalidade = modalidadeRepository.findAll();
		if (modalidade == null) {
			throw new ObjectNotFoundException("Modalidades não encontradas!"
					+ ", Tipo: " + Modalidade.class.getName());
		}
		return modalidade;
	}
	
	public Modalidade find(Integer id) {
		Modalidade modalidade = modalidadeRepository.findOne(id);
		if (modalidade == null) {
			throw new ObjectNotFoundException("Modalidade não encontrada! ID: " + id
					+ ", Tipo: " + Modalidade.class.getName());
		}
		return modalidade;
	}
	
//	public Modalidade insert(Modalidade jogador) {
//		jogador.setId(null);
//		return modalidadeRepository.save(jogador);
//	}
}
