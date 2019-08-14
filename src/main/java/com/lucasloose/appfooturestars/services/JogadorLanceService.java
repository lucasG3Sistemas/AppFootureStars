package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.JogadorLance;
import com.lucasloose.appfooturestars.repositories.JogadorLanceRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class JogadorLanceService {

	@Autowired
	private JogadorLanceRepository jogadorLanceRepository;
	
	public List<JogadorLance> buscarLista() {
		List<JogadorLance> jogadorLance = jogadorLanceRepository.findAll();
		if (jogadorLance == null) {
			throw new ObjectNotFoundException("Lances dos Jogadores não encontrados!"
					+ ", Tipo: " + JogadorLance.class.getName());
		}
		return jogadorLance;
	}
	
	public JogadorLance buscar(Integer id) {
		JogadorLance jogadorLance = jogadorLanceRepository.findOne(id);
		if (jogadorLance == null) {
			throw new ObjectNotFoundException("Lance do Jogador não encontrado! ID: " + id
					+ ", Tipo: " + JogadorLance.class.getName());
		}
		return jogadorLance;
	}
	
}
