package com.lucasloose.appfooturestars.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.dto.JogadorDTO;
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
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<JogadorDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Jogador> listaJogadores = jogadorService.findPage(page, linesPerPage, orderBy, direction);
		Page<JogadorDTO> listDTO = listaJogadores.map(obj -> new JogadorDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody JogadorDTO modalidadeDTO) {
		Jogador mod = jogadorService.fromDTO(modalidadeDTO);
		mod = jogadorService.insert(mod);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mod.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody JogadorDTO jogadorDTO, @PathVariable Integer id) {
		Jogador jogador = jogadorService.fromDTO(jogadorDTO);
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
