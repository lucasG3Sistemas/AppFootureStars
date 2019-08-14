package com.lucasloose.appfooturestars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasloose.appfooturestars.domain.HistoricoContratacao;
import com.lucasloose.appfooturestars.services.HistoricoContratacaoService;

@RestController
@RequestMapping(value="/historicos/contratacoes")
public class HistoricoContratacaoResource {
	
	@Autowired
	private HistoricoContratacaoService historicoContratacaoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<HistoricoContratacao> listaContratacoes = historicoContratacaoService.buscarLista();
		return ResponseEntity.ok().body(listaContratacoes);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		HistoricoContratacao historicoContratacao = historicoContratacaoService.buscar(id);
		return ResponseEntity.ok().body(historicoContratacao);
	}
	
}