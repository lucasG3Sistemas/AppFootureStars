package com.lucasloose.appfooturestars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.services.ClubeFutebolService;

@RestController
@RequestMapping(value="/clubes")
public class ClubeFutebolResource {
	
	@Autowired
	private ClubeFutebolService clubeFutebolService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<ClubeFutebol> listaClubes = clubeFutebolService.buscarLista();
		return ResponseEntity.ok().body(listaClubes);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		ClubeFutebol clubeFutebol = clubeFutebolService.buscar(id);
		return ResponseEntity.ok().body(clubeFutebol);
	}
	
}
