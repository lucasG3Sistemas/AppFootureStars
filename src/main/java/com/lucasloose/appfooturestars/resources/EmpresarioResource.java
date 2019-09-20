package com.lucasloose.appfooturestars.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.dto.EmpresarioDTO;
import com.lucasloose.appfooturestars.dto.EmpresarioNewDTO;
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
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody EmpresarioNewDTO empresarioNewDTO) {
		Empresario empresario = empresarioService.fromDTO(empresarioNewDTO);
		empresario = empresarioService.insert(empresario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(empresario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EmpresarioDTO empresarioDTO, @PathVariable Integer id) {
		Empresario empresario = empresarioService.fromDTO(empresarioDTO);
		empresario.setId(id);
		empresario = empresarioService.update(empresario);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		empresarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
