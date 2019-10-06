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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.dto.ClubeFutebolDTO;
import com.lucasloose.appfooturestars.dto.ClubeFutebolNewDTO;
import com.lucasloose.appfooturestars.services.ClubeFutebolService;

@RestController
@RequestMapping(value="/clubes")
public class ClubeFutebolResource {
	
	@Autowired
	private ClubeFutebolService clubeFutebolService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClubeFutebol>> findAll() {
		List<ClubeFutebol> listaClubes = clubeFutebolService.findAll();
		return ResponseEntity.ok().body(listaClubes);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ClubeFutebol> find(@PathVariable Integer id) {
		ClubeFutebol clubeFutebol = clubeFutebolService.find(id);
		return ResponseEntity.ok().body(clubeFutebol);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	//falta mostrar modalidade
	public ResponseEntity<Page<ClubeFutebolDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<ClubeFutebol> listaClubesFutebol = clubeFutebolService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClubeFutebolDTO> listDTO = listaClubesFutebol.map(obj -> new ClubeFutebolDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<ClubeFutebol> find(@RequestParam(value="value") String email) {
		ClubeFutebol obj = clubeFutebolService.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClubeFutebolNewDTO clubeFutebolNewDTO) {
		ClubeFutebol clubeFutebol = clubeFutebolService.fromDTO(clubeFutebolNewDTO);
		clubeFutebol = clubeFutebolService.insert(clubeFutebol);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clubeFutebol.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClubeFutebolDTO clubeFutebolDTO, @PathVariable Integer id) {
		ClubeFutebol clubeFutebol = clubeFutebolService.fromDTO(clubeFutebolDTO);
		clubeFutebol.setId(id);
		clubeFutebol = clubeFutebolService.update(clubeFutebol);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clubeFutebolService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = clubeFutebolService.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
	
}
