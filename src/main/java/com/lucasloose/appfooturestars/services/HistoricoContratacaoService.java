package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.HistoricoContratacao;
import com.lucasloose.appfooturestars.repositories.HistoricoContratacaoRepository;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class HistoricoContratacaoService {

	@Autowired
	private HistoricoContratacaoRepository historicoContratacaoRepository;
	
	
	public List<HistoricoContratacao> buscarLista() {
		List<HistoricoContratacao> historicoContratacao = historicoContratacaoRepository.findAll();
		if (historicoContratacao == null) {
			throw new ObjectNotFoundException("Histórico de Contratação não encontrado!"
					+ ", Tipo: " + HistoricoContratacao.class.getName());
		}
		return historicoContratacao;
	}
	
	public HistoricoContratacao buscar(Integer id) {
		HistoricoContratacao historicoContratacao = historicoContratacaoRepository.findOne(id);
		if (historicoContratacao == null) {
			throw new ObjectNotFoundException("Histórico de Contratação não encontrado! ID: " + id
					+ ", Tipo: " + HistoricoContratacao.class.getName());
		}
		return historicoContratacao;
	}
	
}
