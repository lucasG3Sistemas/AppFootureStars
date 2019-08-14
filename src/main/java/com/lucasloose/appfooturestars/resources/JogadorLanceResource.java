package com.lucasloose.appfooturestars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucasloose.appfooturestars.domain.JogadorLance;
import com.lucasloose.appfooturestars.services.JogadorLanceService;

@RestController
@RequestMapping(value="/jogadores/lances")
public class JogadorLanceResource {
	
	@Autowired
	private JogadorLanceService jogadorLanceService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> list() {
		List<JogadorLance> listaLancesJogadores = jogadorLanceService.buscarLista();
		return ResponseEntity.ok().body(listaLancesJogadores);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		JogadorLance jogadorLance = jogadorLanceService.buscar(id);
		return ResponseEntity.ok().body(jogadorLance);
	}
	
}
