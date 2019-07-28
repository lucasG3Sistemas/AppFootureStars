package com.lucasloose.appfooturestars.resources;

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
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Modalidade modalidade = modalidadeService.buscar(id);
		return ResponseEntity.ok().body(modalidade);
	}
	
}
