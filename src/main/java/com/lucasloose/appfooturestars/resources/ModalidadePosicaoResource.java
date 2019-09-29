package com.lucasloose.appfooturestars.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.resources.utils.URL;
import com.lucasloose.appfooturestars.services.ModalidadePosicaoService;

@RestController
@RequestMapping(value="/modalidades/posicoes")
public class ModalidadePosicaoResource {
	
	@Autowired
	private ModalidadePosicaoService modalidadePosicaoService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ModalidadePosicao>> findModalidade(@RequestParam(value = "modalidades", defaultValue = "") String modalidades) {
		List<Integer> idsModalidades = URL.decodeInList(modalidades);
		List<ModalidadePosicao> listaModalidadePosicoes = modalidadePosicaoService.findModalidadePosicoes(idsModalidades);
		return ResponseEntity.ok().body(listaModalidadePosicoes);
	}
	
}
