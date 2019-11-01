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
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.domain.enums.Perfil;
import com.lucasloose.appfooturestars.dto.ListaObservacaoDTO;
import com.lucasloose.appfooturestars.dto.ListaObservacaoNewDTO;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.repositories.ListaObservacaoRepository;
import com.lucasloose.appfooturestars.security.UserSS;
import com.lucasloose.appfooturestars.services.exceptions.AuthorizationException;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ListaObservacaoService {

	@Autowired
	private ListaObservacaoRepository listaObservacaoRepository;
	
	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;
	
	@Autowired
	private EmpresarioRepository empresarioRepository;
	
	@Autowired
	private JogadorRepository jogadorRepository;
	
	@Autowired
	private EmailService emailService;
	
	
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
	
	public ListaObservacao findByListaUsuario(String usuario) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !usuario.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		ListaObservacao lista = null;
		Usuario usu = new Usuario(usuario);
		ClubeFutebol clube = clubeFutebolRepository.findByUsuario(usu);
		if (clube != null) {
			lista = listaObservacaoRepository.findByClubeFutebol(clube);
		} else {
			Empresario empr = empresarioRepository.findByUsuario(usu);
			if (empr != null) {
				lista = listaObservacaoRepository.findByEmpresario(empr);
			}
		}
		
//		if (lista == null) {
//			throw new ObjectNotFoundException(
//					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Jogador.class.getName());
//		}
		return lista;
	}

	
	public ListaObservacao fromDTO(ListaObservacaoDTO listaObservacaoDTO) {
//		ClubeFutebol clubeFutebol = new ClubeFutebol(listaObservacaoDTO.getIdClubeFutebol());
//		Empresario empresario = new Empresario(listaObservacaoDTO.getIdEmpresario());
		
		Jogador jogador = jogadorRepository.findOne(listaObservacaoDTO.getIdJogador());
		ListaObservacao listaObservacao = new ListaObservacao(listaObservacaoDTO.getId());
		listaObservacao.getJogadores().add(jogador);
		
		Usuario usu = new Usuario(listaObservacaoDTO.getIdUsuario());
		ClubeFutebol clube = clubeFutebolRepository.findByUsuario(usu);
		if (clube != null) {
			listaObservacao.setClubeFutebol(clube);
		} else {
			Empresario empr = empresarioRepository.findByUsuario(usu);
			if (empr != null) {
				listaObservacao.setEmpresario(empr);
			}
		}

		return listaObservacao;
	}
	
	public ListaObservacao fromDTO(ListaObservacaoNewDTO listaObservacaoNewDTO) {
//		ClubeFutebol clubeFutebol = null;
//		Empresario empresario = null;
//		
//		if (listaObservacaoNewDTO.getIdClubeFutebol() != null) {
//			clubeFutebol = new ClubeFutebol(listaObservacaoNewDTO.getIdClubeFutebol());
//		}
//		if (listaObservacaoNewDTO.getIdEmpresario() != null) {
//			empresario = new Empresario(listaObservacaoNewDTO.getIdEmpresario());
//		}
		Jogador jogador = jogadorRepository.findOne(listaObservacaoNewDTO.getIdJogador());
//		ListaObservacao listaObservacao = new ListaObservacao(null, clubeFutebol, empresario);
		ListaObservacao listaObservacao = new ListaObservacao(null);
		listaObservacao.getJogadores().add(jogador);
		
		Usuario usu = new Usuario(listaObservacaoNewDTO.getIdUsuario());
		ClubeFutebol clube = clubeFutebolRepository.findByUsuario(usu);
		if (clube != null) {
			listaObservacao.setClubeFutebol(clube);
		} else {
			Empresario empr = empresarioRepository.findByUsuario(usu);
			if (empr != null) {
				listaObservacao.setEmpresario(empr);
			}
		}
		
		return listaObservacao;
	}
	
	@Transactional
	public ListaObservacao insert(ListaObservacao listaObservacao) {
		listaObservacao.setId(null);
		if (listaObservacao.getClubeFutebol() != null) {
			listaObservacao.setClubeFutebol(clubeFutebolRepository.findOne(listaObservacao.getClubeFutebol().getId()));		
			this.emailService.sendOrderConfirmationEmail(listaObservacao);
		}
		return listaObservacaoRepository.save(listaObservacao);
	}
	
	public ListaObservacao update(ListaObservacao listaObservacao) {
		ListaObservacao newListaObservacao = this.find(listaObservacao.getId());
		this.updateData(newListaObservacao, listaObservacao);
//		newListaObservacao.setClubeFutebol(clubeFutebolRepository.findOne(listaObservacao.getClubeFutebol().getId()));
//		System.out.println(newListaObservacao);
		if (newListaObservacao.getClubeFutebol() != null) {
			this.emailService.sendOrderConfirmationEmail(newListaObservacao);
		}
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
