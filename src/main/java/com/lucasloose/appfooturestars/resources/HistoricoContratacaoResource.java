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

import com.lucasloose.appfooturestars.domain.HistoricoContratacao;
import com.lucasloose.appfooturestars.dto.HistoricoContratacaoNewDTO;
import com.lucasloose.appfooturestars.services.HistoricoContratacaoService;

@RestController
@RequestMapping(value="/historicos/contratacoes")
public class HistoricoContratacaoResource {
	
	@Autowired
	private HistoricoContratacaoService historicoContratacaoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<HistoricoContratacao>> findAll() {
		List<HistoricoContratacao> listaContratacoes = historicoContratacaoService.findAll();
		return ResponseEntity.ok().body(listaContratacoes);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<HistoricoContratacao> find(@PathVariable Integer id) {
		HistoricoContratacao historicoContratacao = historicoContratacaoService.find(id);
		return ResponseEntity.ok().body(historicoContratacao);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody HistoricoContratacaoNewDTO historicoContratacaoNewDTO) {
		HistoricoContratacao historicoContratacao = historicoContratacaoService.fromDTO(historicoContratacaoNewDTO);
		historicoContratacao = historicoContratacaoService.insert(historicoContratacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(historicoContratacao).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
