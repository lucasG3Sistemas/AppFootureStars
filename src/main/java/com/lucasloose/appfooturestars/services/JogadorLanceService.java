package com.lucasloose.appfooturestars.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.JogadorLance;
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.domain.enums.Perfil;
import com.lucasloose.appfooturestars.dto.JogadorLanceDTO;
import com.lucasloose.appfooturestars.dto.JogadorLanceNewDTO;
import com.lucasloose.appfooturestars.repositories.JogadorLanceRepository;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.security.UserSS;
import com.lucasloose.appfooturestars.services.exceptions.AuthorizationException;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class JogadorLanceService {

	@Autowired
	private JogadorLanceRepository jogadorLanceRepository;
	
	@Autowired
	private JogadorRepository jogadorRepository;
	
	
	public Page<JogadorLance> search(List<Integer> idsJogadores, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		//busca lances a partir de um jogador
		List<Jogador> jogadores = jogadorRepository.findAll(idsJogadores);
		return jogadorLanceRepository.findByJogadorIn(jogadores, pageRequest);
	}
	
//	public List<JogadorLance> findAll() {
//		List<JogadorLance> jogadorLance = jogadorLanceRepository.findAll();
//		if (jogadorLance == null) {
//			throw new ObjectNotFoundException("Lances dos Jogadores não encontrados!"
//					+ ", Tipo: " + JogadorLance.class.getName());
//		}
//		return jogadorLance;
//	}
	
	public Page<JogadorLance> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return jogadorLanceRepository.findAll(pageRequest);
	}
	
	public JogadorLance find(Integer id) {
		JogadorLance jogadorLance = jogadorLanceRepository.findOne(id);
		if (jogadorLance == null) {
			throw new ObjectNotFoundException("Lance do Jogador não encontrado! ID: " + id
					+ ", Tipo: " + JogadorLance.class.getName());
		}
		return jogadorLance;
	}
	
	public List<JogadorLance> findByLancesJogador(String usuario) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !usuario.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		List<JogadorLance> lista = null;
		Usuario usu = new Usuario(usuario);
		Jogador jogador = jogadorRepository.findByUsuario(usu);
		if (jogador != null) {
			lista = jogadorLanceRepository.findByJogadorOrderByIdDesc(jogador);
		}
		
		if (lista == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Jogador.class.getName());
		}
		return lista;
		
	}
	
	public List<JogadorLance> findByLancesIdJogador(Integer idJogador) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("Acesso negado");
		}
		
		List<JogadorLance> lista = null;
		Jogador jogador = jogadorRepository.findOne(idJogador);
		if (jogador != null) {
			lista = jogadorLanceRepository.findByJogadorOrderByIdDesc(jogador);
		}
		if (lista == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Jogador.class.getName());
		}
		return lista;
		
	}
	
	public JogadorLance fromDTO(JogadorLanceDTO jogadorLanceDTO) {
		Jogador jogador = new Jogador(jogadorLanceDTO.getIdJogador(), "");
		String urlVideo = jogadorLanceDTO.getUrlVideo();
		//https://www.youtube.com/watch?v=59WkZv1xNl0
		String palavra = "watch?v=";
		String novaPalavra = "embed/";
		String novaUrl = urlVideo.replace(palavra,novaPalavra);
		JogadorLance jogadorLance = new JogadorLance(jogadorLanceDTO.getId(), jogadorLanceDTO.getTitulo(), novaUrl, jogadorLanceDTO.getDescricao(), 
				jogadorLanceDTO.getData_publicacao(), 
//				jogadorLanceDTO.getPais_atual(), jogadorLanceDTO.getEstado_atual(), jogadorLanceDTO.getMunicipio_atual(),
				jogadorLanceDTO.getComplemento(), jogador);
		return jogadorLance;
	}
	
	public JogadorLance fromDTO(JogadorLanceNewDTO jogadorLanceNewDTO) {
		Integer idJogador = 0;
		if (jogadorLanceNewDTO.getIdJogador() == null) {
			Jogador jog = this.jogadorRepository.findByEmail(jogadorLanceNewDTO.getIdUsuario());
			idJogador = jog.getId();
		} else {
			idJogador = jogadorLanceNewDTO.getIdJogador();
		}
		
		Jogador jogador = new Jogador(idJogador, "");
		String urlVideo = jogadorLanceNewDTO.getUrlVideo();
		String palavra = "watch?v=";
		String novaPalavra = "embed/";
		String novaUrl = urlVideo.replace(palavra,novaPalavra);
		String www = "m.youtube.com";
		String novaWww = "www.youtube.com";
		novaUrl = novaUrl.replace(www, novaWww);
		JogadorLance jogadorLance = new JogadorLance(null, jogadorLanceNewDTO.getTitulo(), novaUrl, jogadorLanceNewDTO.getDescricao(),
				new Date(), 
//				jogadorLanceNewDTO.getPais_atual(), jogadorLanceNewDTO.getEstado_atual(), jogadorLanceNewDTO.getMunicipio_atual(),
				jogadorLanceNewDTO.getComplemento(), jogador);
		return jogadorLance;
	}
	
	@Transactional
	public JogadorLance insert(JogadorLance jogadorLance) {
		jogadorLance.setId(null);
		jogadorLance.setData_publicacao(new Date());
		return jogadorLanceRepository.save(jogadorLance);
	}
	
	public JogadorLance update(JogadorLance jogadorLance) {
		JogadorLance newJogadorLance = this.find(jogadorLance.getId());
		this.updateData(newJogadorLance, jogadorLance);
		return jogadorLanceRepository.save(newJogadorLance);
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			jogadorLanceRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Erro ao Excluir o Lance! Tente novamente!");
		}
	}
	
	private void updateData(JogadorLance newJogadorLance, JogadorLance jogadorLance) {
		newJogadorLance.setTitulo(jogadorLance.getTitulo());
		newJogadorLance.setUrlVideo(jogadorLance.getUrlVideo());
		newJogadorLance.setDescricao(jogadorLance.getDescricao());
//		newJogadorLance.setData_publicacao(newJogadorLance.getData_publicacao());
//		newJogadorLance.setPais_atual(jogadorLance.getPais_atual());
//		newJogadorLance.setEstado_atual(jogadorLance.getEstado_atual());
//		newJogadorLance.setMunicipio_atual(jogadorLance.getMunicipio_atual());
		newJogadorLance.setComplemento(jogadorLance.getComplemento());
		newJogadorLance.setJogador(jogadorLance.getJogador());
	}
	
}
