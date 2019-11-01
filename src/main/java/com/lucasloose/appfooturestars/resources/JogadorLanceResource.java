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
import com.lucasloose.appfooturestars.domain.JogadorLance;
import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.dto.JogadorLanceDTO;
import com.lucasloose.appfooturestars.dto.JogadorLanceNewDTO;
import com.lucasloose.appfooturestars.resources.utils.URL;
import com.lucasloose.appfooturestars.services.JogadorLanceService;

@RestController
@RequestMapping(value="/jogadores/lances")
public class JogadorLanceResource {
	
	@Autowired
	private JogadorLanceService jogadorLanceService;
	
	
//	@RequestMapping(method=RequestMethod.GET)
//	public ResponseEntity<List<JogadorLance>> findAll() {
//		List<JogadorLance> listaLancesJogadores = jogadorLanceService.findAll();
//		return ResponseEntity.ok().body(listaLancesJogadores);
//	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<JogadorLance> find(@PathVariable Integer id) {
		JogadorLance jogadorLance = jogadorLanceService.find(id);
		return ResponseEntity.ok().body(jogadorLance);
	}
	
	@RequestMapping(value="/jogador", method=RequestMethod.GET)
	public ResponseEntity<List<JogadorLance>> findLances(@RequestParam(value="usuario") String usuario) {
		List<JogadorLance> obj = jogadorLanceService.findByLancesJogador(usuario);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/idJogador", method=RequestMethod.GET)
	public ResponseEntity<List<JogadorLance>> findLancesJogador(@RequestParam(value="idJogador") String idJogador) {
		List<JogadorLance> obj = jogadorLanceService.findByLancesIdJogador(Integer.parseInt(idJogador));
		return ResponseEntity.ok().body(obj);
	}
	
//	@RequestMapping(value="/lista", method=RequestMethod.GET)
//	public ResponseEntity<List<Jogador>> findLista(@RequestParam(value="idLista") String idLista, @RequestParam(value="usuario") String idUsuario) {
//		List<Jogador> listaJogadores = jogadorService.findLista(Integer.parseInt(idLista), idUsuario);
//		return ResponseEntity.ok().body(listaJogadores);
//	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<JogadorLanceDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "data_publicacao") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<JogadorLance> listaJogadoresLances = jogadorLanceService.findPage(page, linesPerPage, orderBy, direction);
		Page<JogadorLanceDTO> listDTO = listaJogadoresLances.map(obj -> new JogadorLanceDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<JogadorLanceDTO>> findPage(
			@RequestParam(value = "jogador", defaultValue = "") String jogador,
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "D ") String direction) {
		List<Integer> idsJogadores = URL.decodeInList(jogador);
		Page<JogadorLance> listaJogadoresLances = jogadorLanceService.search(idsJogadores, page, linesPerPage, orderBy, direction);
		Page<JogadorLanceDTO> listDTO = listaJogadoresLances.map(obj -> new JogadorLanceDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody JogadorLanceNewDTO jogadorLanceNewDTO) {
		JogadorLance jogadorLance = jogadorLanceService.fromDTO(jogadorLanceNewDTO);
		jogadorLance = jogadorLanceService.insert(jogadorLance);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(jogadorLance.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody JogadorLanceDTO jogadorLanceDTO, @PathVariable Integer id) {
		JogadorLance jogadorLance = jogadorLanceService.fromDTO(jogadorLanceDTO);
		jogadorLance.setId(id);
		jogadorLance = jogadorLanceService.update(jogadorLance);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		jogadorLanceService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
