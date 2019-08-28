package com.lucasloose.appfooturestars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.services.ModalidadeService;

@RestController
@RequestMapping(value="/modalidades")
public class ModalidadeResource {
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<Modalidade> listaModalidades = modalidadeService.findAll();
		return ResponseEntity.ok().body(listaModalidades);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Modalidade> find(@PathVariable Integer id) {
		Modalidade modalidade = modalidadeService.find(id);
		return ResponseEntity.ok().body(modalidade);
	}
	
//	@RequestMapping(value="/insert", method=RequestMethod.POST)
//	public ResponseEntity<Void> insert(@RequestBody Modalidade jogador) {
//		jogador = modalidadeService.insert(jogador);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogador.getId()).toUri();
//		return ResponseEntity.created(uri).build();
//	}
}
