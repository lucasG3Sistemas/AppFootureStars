package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	public List<Jogador> buscarLista() {
		List<Jogador> jogador = jogadorRepository.findAll();
		if (jogador == null) {
			throw new ObjectNotFoundException("Jogadores não encontrados!"
					+ ", Tipo: " + Jogador.class.getName());
		}
		return jogador;
	}
	
	public Jogador buscar(Integer id) {
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
}
