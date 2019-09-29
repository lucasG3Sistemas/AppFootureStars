package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.repositories.ModalidadePosicaoRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadeRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ModalidadePosicaoService {

	@Autowired
	private ModalidadePosicaoRepository modalidadePosicaoRepository;
	
	@Autowired
	private ModalidadeRepository modalidadeRepository;
	
	
	public List<ModalidadePosicao> findModalidadePosicoes(List<Integer> idsModalidades) {
		List<Modalidade> modalidades = modalidadeRepository.findAll(idsModalidades);
		List<ModalidadePosicao> modalidadePosicoes = modalidadePosicaoRepository.findByModalidadesIn(modalidades);
		if (modalidadePosicoes == null) {
			throw new ObjectNotFoundException("Posições de Modalidades não encontradas!"
					+ ", Tipo: " + Jogador.class.getName());
		}
		return modalidadePosicoes;
	}
		
}
