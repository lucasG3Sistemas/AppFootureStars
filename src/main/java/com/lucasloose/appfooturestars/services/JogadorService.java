package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	public List<Jogador> findAll() {
		List<Jogador> jogador = jogadorRepository.findAll();
		if (jogador == null) {
			throw new ObjectNotFoundException("Jogadores não encontrados!"
					+ ", Tipo: " + Jogador.class.getName());
		}
		return jogador;
	}
	
	public Jogador find(Integer id) {
		Jogador jogador = jogadorRepository.findOne(id);
		if (jogador == null) {
			throw new ObjectNotFoundException("Jogador não encontrado! ID: " + id
					+ ", Tipo: " + Jogador.class.getName());
		}
		return jogador;
	}
	
	public Jogador insert(Jogador jogador) {
		jogador.setId(null);
		return jogadorRepository.save(jogador);
	}
	
	public Jogador update(Jogador jogador) {
		this.find(jogador.getId());
		return jogadorRepository.save(jogador);
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			jogadorRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Jogador que possui tal coisa");
		}
	}
	
}
