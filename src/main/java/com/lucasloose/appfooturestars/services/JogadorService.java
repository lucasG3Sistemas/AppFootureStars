package com.lucasloose.appfooturestars.services;

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
import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.dto.JogadorDTO;
import com.lucasloose.appfooturestars.dto.JogadorNewDTO;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadePosicaoRepository;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	
	@Autowired
	private ModalidadePosicaoRepository posicaoRepository;
	
	
	public Page<Jogador> search(String nome, List<Integer> idsPosicoes, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		//busca jogadores a partir de uma posição
		List<ModalidadePosicao> posicoes = posicaoRepository.findAll(idsPosicoes);
		return jogadorRepository.findDistinctByNomeContainingAndPosicoesIn(nome, posicoes, pageRequest);
	}
	
	public List<Jogador> findAll() {
		List<Jogador> jogador = jogadorRepository.findAll();
		if (jogador == null) {
			throw new ObjectNotFoundException("Jogadores não encontrados!"
					+ ", Tipo: " + Jogador.class.getName());
		}
		return jogador;
	}
	
	public Jogador find(Integer id) {
//		UserSS user = UserService.authenticated();
//		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado");
//		}
		
		Jogador jogador = jogadorRepository.findOne(id);
		if (jogador == null) {
			throw new ObjectNotFoundException("Jogador não encontrado! ID: " + id
					+ ", Tipo: " + Jogador.class.getName());
		}
	
		return jogador;
	}
	
	public Page<Jogador> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return jogadorRepository.findAll(pageRequest);
	}
	
	public Jogador fromDTO(JogadorDTO jogadorDTO) {
		Modalidade modalidade = new Modalidade(jogadorDTO.getIdModalidade(), "");
		ClubeFutebol clube = new ClubeFutebol(jogadorDTO.getIdClubeFutebol());
		Empresario empresario = new Empresario(jogadorDTO.getIdEmpresario());
		Usuario usuario = new Usuario(jogadorDTO.getIdUsuario());
		Jogador jogador = new Jogador(jogadorDTO.getId(), jogadorDTO.getNome(), jogadorDTO.getFoto(), jogadorDTO.getCpf(), null,
				jogadorDTO.getNacionalidade(), jogadorDTO.getEstado_nasc(), jogadorDTO.getMunicipio_nasc(), jogadorDTO.getSexo(), jogadorDTO.getAltura(), jogadorDTO.getPeso(), jogadorDTO.getProfissionalizacao(),
				jogadorDTO.getCodigo_cbf(), modalidade, jogadorDTO.getPerna_preferida(), jogadorDTO.getPrefixo_fone(), jogadorDTO.getDdd_fone(), jogadorDTO.getFone(),
				jogadorDTO.getEmail(), jogadorDTO.getComplemento(), clube, empresario, usuario);
//		Modalidade mod = new Modalidade(jogadorDTO.getIdModalidade(), "");
//		jogador.getModalidades().add(mod);
		ModalidadePosicao pos1 = new ModalidadePosicao(jogadorDTO.getIdPosicao1(), "");
		jogador.getPosicoes().add(pos1);
		
		if (jogadorDTO.getIdPosicao2() != null) {
			ModalidadePosicao pos2 = new ModalidadePosicao(jogadorDTO.getIdPosicao2(), "");
			jogador.getPosicoes().add(pos2);
		}
		if (jogadorDTO.getIdPosicao3() != null) {
			ModalidadePosicao pos3 = new ModalidadePosicao(jogadorDTO.getIdPosicao3(), "");
			jogador.getPosicoes().add(pos3);
		}
		
		return jogador;
	}
	
	public Jogador fromDTO(JogadorNewDTO jogadorNewDTO) {
		//passei a data como null
		Modalidade modalidade = new Modalidade(jogadorNewDTO.getIdModalidade(), "");
		ClubeFutebol clube = new ClubeFutebol(jogadorNewDTO.getIdClubeFutebol());
		Empresario empresario = new Empresario(jogadorNewDTO.getIdEmpresario());
		Usuario usuario = new Usuario(jogadorNewDTO.getIdUsuario());
		Jogador jogador = new Jogador(null, jogadorNewDTO.getNome(), jogadorNewDTO.getFoto(), jogadorNewDTO.getCpf(), null,
				jogadorNewDTO.getNacionalidade(), jogadorNewDTO.getEstado_nasc(), jogadorNewDTO.getMunicipio_nasc(), jogadorNewDTO.getSexo(), jogadorNewDTO.getAltura(), jogadorNewDTO.getPeso(), jogadorNewDTO.getProfissionalizacao(),
				jogadorNewDTO.getCodigo_cbf(), modalidade, jogadorNewDTO.getPerna_preferida(), jogadorNewDTO.getPrefixo_fone(), jogadorNewDTO.getDdd_fone(), jogadorNewDTO.getFone(),
				jogadorNewDTO.getEmail(), jogadorNewDTO.getComplemento(), clube, empresario, usuario);
//		Modalidade mod = new Modalidade(jogadorNewDTO.getIdModalidade(), "");
//		jogador.getModalidades().add(mod);
		ModalidadePosicao pos1 = new ModalidadePosicao(jogadorNewDTO.getIdPosicao1(), "");
		jogador.getPosicoes().add(pos1);
		if (jogadorNewDTO.getIdPosicao2() != null) {
			ModalidadePosicao pos2 = new ModalidadePosicao(jogadorNewDTO.getIdPosicao2(), "");
			jogador.getPosicoes().add(pos2);
		}
		if (jogadorNewDTO.getIdPosicao3() != null) {
			ModalidadePosicao pos3 = new ModalidadePosicao(jogadorNewDTO.getIdPosicao3(), "");
			jogador.getPosicoes().add(pos3);
		}
		
		return jogador;
	}
	
	@Transactional
	public Jogador insert(Jogador jogador) {
		jogador.setId(null);
//		jogador = jogadorRepository.save(jogador);
//		outroRepository.save(obj.getOUTRO());
//		return obj;
		return jogadorRepository.save(jogador);
	}
	
	public Jogador update(Jogador jogador) {
		Jogador newJogador = this.find(jogador.getId());
		this.updateData(newJogador, jogador);
		return jogadorRepository.save(newJogador);
	}
	
	public void delete(Integer id) {
		this.find(id);
		try {
			jogadorRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Jogador pq há entidades relacionadas");
		}
	}
	
	private void updateData(Jogador newJogador, Jogador jogador) {
		newJogador.setNome(jogador.getNome());
		newJogador.setFoto(jogador.getFoto());
		newJogador.setCpf(jogador.getCpf() != null ? jogador.getCpf() : newJogador.getCpf());
		newJogador.setData_nasc(jogador.getData_nasc());
		newJogador.setNacionalidade(jogador.getNacionalidade());
		newJogador.setEstado_nasc(jogador.getEstado_nasc());
		newJogador.setMunicipio_nasc(jogador.getMunicipio_nasc());
		newJogador.setSexo(jogador.getSexo());
		newJogador.setAltura(jogador.getAltura());
		newJogador.setPeso(jogador.getPeso());
		newJogador.setProfissionalizacao(jogador.getProfissionalizacao());
		newJogador.setCodigo_cbf(jogador.getCodigo_cbf());
		newJogador.setModalidade(jogador.getModalidade());
//		if (jogador.getModalidades() != null) {
//			newJogador.getModalidades().removeAll(newJogador.getModalidades());
//			for (int i=0; i<jogador.getModalidades().size(); i++) {
//				newJogador.getModalidades().add(jogador.getModalidades().get(i));
//			}
//		}
		if (jogador.getPosicoes() != null) {
			newJogador.getPosicoes().removeAll(newJogador.getPosicoes());
			for (int i=0; i<jogador.getPosicoes().size(); i++) {
				newJogador.getPosicoes().add(jogador.getPosicoes().get(i));
			}
		}
		newJogador.setPerna_preferida(jogador.getPerna_preferida());
		newJogador.setPrefixo_fone(jogador.getPrefixo_fone());
		newJogador.setDdd_fone(jogador.getDdd_fone());
		newJogador.setFone(jogador.getFone());
		newJogador.setEmail(jogador.getEmail());
		//lances
		newJogador.setComplemento(jogador.getComplemento());
		newJogador.setClubeFutebol(jogador.getClubeFutebol());
		newJogador.setEmpresario(jogador.getEmpresario());
		//lista observacao
		//lista historico
		newJogador.setUsuario(jogador.getUsuario());
	}
	
}
