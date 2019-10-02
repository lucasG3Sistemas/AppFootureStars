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

import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.dto.UsuarioNewDTO;
import com.lucasloose.appfooturestars.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@RequestMapping(value="/{login}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable String login) {
		Usuario usuario = usuarioService.find(login);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario = usuarioService.fromDTO(usuarioNewDTO);
		usuario = usuarioService.insert(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{login}").buildAndExpand(usuario.getLogin()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{login}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String login) {
		usuarioService.delete(login);
		return ResponseEntity.noContent().build();
	}
	
}
