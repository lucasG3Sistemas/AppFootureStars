package com.lucasloose.appfooturestars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.services.ListaObservacaoService;

@RestController
@RequestMapping(value="/listas/observacoes")
public class ListaObservacaoResource {
	
	@Autowired
	private ListaObservacaoService listaObservacaoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ListaObservacao>> findAll() {
		List<ListaObservacao> listas = listaObservacaoService.findAll();
		return ResponseEntity.ok().body(listas);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ListaObservacao> find(@PathVariable Integer id) {
		ListaObservacao listaObservacao = listaObservacaoService.find(id);
		return ResponseEntity.ok().body(listaObservacao);
	}
	
}
