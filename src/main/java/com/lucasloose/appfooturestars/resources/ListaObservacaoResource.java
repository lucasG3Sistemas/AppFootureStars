 package com.lucasloose.appfooturestars.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.dto.ListaObservacaoDTO;
import com.lucasloose.appfooturestars.dto.ListaObservacaoNewDTO;
import com.lucasloose.appfooturestars.resources.utils.URL;
import com.lucasloose.appfooturestars.services.ListaObservacaoService;

@RestController
@RequestMapping(value="/listas/observacoes")
public class ListaObservacaoResource {
	
	@Autowired
	private ListaObservacaoService listaObservacaoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ListaObservacao>> findAll() {
		List<ListaObservacao> listas = listaObservacaoService.findAll();
		return ResponseEntity.ok().body(listas);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ListaObservacao> find(@PathVariable Integer id) {
		ListaObservacao listaObservacao = listaObservacaoService.find(id);
		return ResponseEntity.ok().body(listaObservacao);
	}
	
	@RequestMapping(value="/usuario", method=RequestMethod.GET)
	public ResponseEntity<ListaObservacao> findListaClube(@RequestParam(value="value") String usuario) {
		ListaObservacao obj = listaObservacaoService.findByListaUsuario(usuario);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ListaObservacaoNewDTO listaObservacaoNewDTO) {
		ListaObservacao listaObservacao = listaObservacaoService.fromDTO(listaObservacaoNewDTO);
		listaObservacao = listaObservacaoService.insert(listaObservacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(listaObservacao).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ListaObservacaoDTO listaObservacaoDTO, @PathVariable Integer id) {
		ListaObservacao listaObservacao = listaObservacaoService.fromDTO(listaObservacaoDTO);
		listaObservacao.setId(id);
		listaObservacao = listaObservacaoService.update(listaObservacao);
		return ResponseEntity.noContent().build();
	}
	
	//Exclui jogador da lista 
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteJogadorLista(@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "jogadores", defaultValue = "") String jogadores) {
		List<Integer> idsJogadores = URL.decodeInList(jogadores);
		listaObservacaoService.deleteJogadorLista(Integer.parseInt(id), idsJogadores);
		return ResponseEntity.noContent().build();
	}
	
	//Exclui a lista inteira
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		listaObservacaoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
