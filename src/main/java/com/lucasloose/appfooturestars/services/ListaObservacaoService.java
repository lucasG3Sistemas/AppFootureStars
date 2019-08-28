package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.repositories.ListaObservacaoRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ListaObservacaoService {

	@Autowired
	private ListaObservacaoRepository listaObservacaoRepository;
	
	public List<ListaObservacao> findAll() {
		List<ListaObservacao> listaObservacao = listaObservacaoRepository.findAll();
		if (listaObservacao == null) {
			throw new ObjectNotFoundException("Listas de Observação não encontradas!"
					+ ", Tipo: " + Jogador.class.getName());
		}
		return listaObservacao;
	}
	
	public ListaObservacao find(Integer id) {
		ListaObservacao listaObservacao = listaObservacaoRepository.findOne(id);
		if (listaObservacao == null) {
			throw new ObjectNotFoundException("Lista de Observação não encontrada! ID: " + id
					+ ", Tipo: " + ListaObservacao.class.getName());
		}
		return listaObservacao;
	}
	
}
