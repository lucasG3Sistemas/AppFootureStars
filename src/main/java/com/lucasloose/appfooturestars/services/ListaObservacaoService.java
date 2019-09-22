package com.lucasloose.appfooturestars.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.ListaObservacao;
import com.lucasloose.appfooturestars.dto.ListaObservacaoDTO;
import com.lucasloose.appfooturestars.dto.ListaObservacaoNewDTO;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.repositories.ListaObservacaoRepository;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ListaObservacaoService {

	@Autowired
	private ListaObservacaoRepository listaObservacaoRepository;
	
	@Autowired
	private JogadorRepository jogadorRepository;
	
	
	public List<ListaObservacao> findAll() {
		List<ListaObservacao> listaObservacao = listaObservacaoRepository.findAll();
		if (listaObservacao == null) {
			throw new ObjectNotFoundException("Listas de Observação não encontradas!"
					+ ", Tipo: " + Jogador.class.getName());
		}
		return listaObservacao;
	}
	
	public ListaObservacao find(Integer id) {
		ListaObservacao listaObservacao = listaObservacaoRepository.findOne(id);
		if (listaObservacao == null) {
			throw new ObjectNotFoundException("Lista de Observação não encontrada! ID: " + id
					+ ", Tipo: " + ListaObservacao.class.getName());
		}
		return listaObservacao;
	}
	
	public ListaObservacao fromDTO(ListaObservacaoDTO listaObservacaoDTO) {
//		ClubeFutebol clubeFutebol = new ClubeFutebol(listaObservacaoDTO.getIdClubeFutebol());
//		Empresario empresario = new Empresario(listaObservacaoDTO.getIdEmpresario());
		Jogador jogador = new Jogador(listaObservacaoDTO.getIdJogador(), "");
		ListaObservacao listaObservacao = new ListaObservacao(listaObservacaoDTO.getId());
		listaObservacao.getJogadores().add(jogador);
	
		return listaObservacao;
	}
	
	public ListaObservacao fromDTO(ListaObservacaoNewDTO listaObservacaoNewDTO) {
		ClubeFutebol clubeFutebol = null;
		Empresario empresario = null;
		
		if (listaObservacaoNewDTO.getIdClubeFutebol() != null) {
			clubeFutebol = new ClubeFutebol(listaObservacaoNewDTO.getIdClubeFutebol());
		}
		if (listaObservacaoNewDTO.getIdEmpresario() != null) {
			empresario = new Empresario(listaObservacaoNewDTO.getIdEmpresario());
		}
		Jogador jogador = new Jogador(listaObservacaoNewDTO.getIdJogador(), "");
		ListaObservacao listaObservacao = new ListaObservacao(null, clubeFutebol, empresario);
		listaObservacao.getJogadores().add(jogador);
		
		return listaObservacao;
	}
	
	@Transactional
	public ListaObservacao insert(ListaObservacao listaObservacao) {
		listaObservacao.setId(null);
		return listaObservacaoRepository.save(listaObservacao);
	}
	
	public ListaObservacao update(ListaObservacao listaObservacao) {
		ListaObservacao newListaObservacao = this.find(listaObservacao.getId());
		this.updateData(newListaObservacao, listaObservacao);
		return listaObservacaoRepository.save(newListaObservacao);
	}
	
	private void updateData(ListaObservacao newListaObservacao, ListaObservacao listaObservacao) {
		newListaObservacao.setClubeFutebol(newListaObservacao.getClubeFutebol());
		newListaObservacao.setEmpresario(newListaObservacao.getEmpresario());
		if (listaObservacao.getJogadores() != null) {
			for (int i=0; i<listaObservacao.getJogadores().size(); i++) {
				newListaObservacao.getJogadores().add(listaObservacao.getJogadores().get(i));
			}
		}
	}
	
	//Excluir jogador da lista
	public void deleteJogadorLista(Integer id, List<Integer> idsJogadores) {
		try {
			List<Jogador> jogadores = jogadorRepository.findAll(idsJogadores);
			List<ListaObservacao> lista = listaObservacaoRepository.findByIdAndJogadoresIn(id, jogadores);
			String a = "";
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao excluir Jogador da Lista de Observação!");
		}	
	}
	
	//Exclui a lista inteira
	public void delete(Integer id) {
		this.find(id);
		try {
			listaObservacaoRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao excluir a Lista de Observação!");
		}
	}
	
}
