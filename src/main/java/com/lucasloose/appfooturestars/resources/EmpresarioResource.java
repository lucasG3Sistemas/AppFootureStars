package com.lucasloose.appfooturestars.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.services.EmpresarioService;

@RestController
@RequestMapping(value="/empresarios")
public class EmpresarioResource {
	
	@Autowired
	private EmpresarioService empresarioService;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Empresario> find(@PathVariable Integer id) {
		Empresario empresario = empresarioService.find(id);
		return ResponseEntity.ok().body(empresario);
	}
	
}
