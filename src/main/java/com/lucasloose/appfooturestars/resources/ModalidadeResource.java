package com.lucasloose.appfooturestars.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.dto.ModalidadeDTO;
import com.lucasloose.appfooturestars.services.ModalidadeService;

@RestController
@RequestMapping(value="/modalidades")
public class ModalidadeResource {
	
	@Autowired
	private ModalidadeService modalidadeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ModalidadeDTO>> findAll() {
		List<Modalidade> listaModalidades = modalidadeService.findAll();
		List<ModalidadeDTO> listDTO = listaModalidades.stream().map(obj -> new ModalidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Modalidade> find(@PathVariable Integer id) {
		Modalidade modalidade = modalidadeService.find(id);
		return ResponseEntity.ok().body(modalidade);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ModalidadeDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Modalidade> listaModalidades = modalidadeService.findPage(page, linesPerPage, orderBy, direction);
		Page<ModalidadeDTO> listDTO = listaModalidades.map(obj -> new ModalidadeDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ModalidadeDTO modalidadeDTO) {
		Modalidade mod = modalidadeService.fromDTO(modalidadeDTO);
		mod = modalidadeService.insert(mod);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mod.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ModalidadeDTO modDTO, @PathVariable Integer id) {
		Modalidade jogador = modalidadeService.fromDTO(modDTO);
		jogador.setId(id);
		jogador = modalidadeService.update(jogador);
		return ResponseEntity.noContent().build();
	}
}
