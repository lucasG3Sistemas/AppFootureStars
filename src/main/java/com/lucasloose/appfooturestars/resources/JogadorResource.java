package com.lucasloose.appfooturestars.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.services.JogadorService;

@RestController
@RequestMapping(value="/jogadores")
public class JogadorResource {
	
	@Autowired
	private JogadorService jogadorService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Jogador>> findAll() {
		List<Jogador> listaJogadores = jogadorService.findAll();
		return ResponseEntity.ok().body(listaJogadores);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Jogador> find(@PathVariable Integer id) {
		Jogador jogador = jogadorService.find(id);
		return ResponseEntity.ok().body(jogador);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Jogador jogador) {
		jogador = jogadorService.insert(jogador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogador.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Jogador jogador, @PathVariable Integer id) {
		jogador.setId(id);
		jogador = jogadorService.update(jogador);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		jogadorService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
