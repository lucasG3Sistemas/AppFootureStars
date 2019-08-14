package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ClubeFutebolService {

	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;
	
	public List<ClubeFutebol> buscarLista() {
		List<ClubeFutebol> clubeFutebol = clubeFutebolRepository.findAll();
		if (clubeFutebol == null) {
			throw new ObjectNotFoundException("Clubes de Futebol não encontrados!"
					+ ", Tipo: " + ClubeFutebol.class.getName());
		}
		return clubeFutebol;
	}
	
	public ClubeFutebol buscar(Integer id) {
		ClubeFutebol clubeFutebol = clubeFutebolRepository.findOne(id);
		if (clubeFutebol == null) {
			throw new ObjectNotFoundException("Clube de Futebol não encontrado! ID: " + id
					+ ", Tipo: " + ClubeFutebol.class.getName());
		}
		return clubeFutebol;
	}
	
}
