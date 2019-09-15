package com.lucasloose.appfooturestars.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.dto.ListaObservacaoDTO;
import com.lucasloose.appfooturestars.dto.ListaObservacaoNewDTO;
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
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ListaObservacaoNewDTO listaObservacaoNewDTO) {
		ListaObservacao listaObservacao = listaObservacaoService.fromDTO(listaObservacaoNewDTO);
		listaObservacao = listaObservacaoService.insert(listaObservacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(listaObservacao).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ListaObservacaoDTO listaObservacaoDTO, @PathVariable Integer id) {
		ListaObservacao listaObservacao = listaObservacaoService.fromDTO(listaObservacaoDTO);
		listaObservacao.setId(id);
		listaObservacao = listaObservacaoService.update(listaObservacao);
		return ResponseEntity.noContent().build();
	}
	
}
