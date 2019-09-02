package com.lucasloose.appfooturestars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.dto.JogadorDTO;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;
	
	public List<Jogador> findAll() {
		List<Jogador> jogador = jogadorRepository.findAll();
		if (jogador == null) {
			throw new ObjectNotFoundException("Jogadores não encontrados!"
					+ ", Tipo: " + Jogador.class.getName());
		}
		return jogador;
	}
	
	public Jogador find(Integer id) {
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
		return new Jogador(jogadorDTO.getId(), jogadorDTO.getNome(), jogadorDTO.getEmail());
	}
	
	public Jogador insert(Jogador jogador) {
		jogador.setId(null);
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
//		newJogador.setData_nasc(jogador.getData_nasc());
//		newJogador.setNacionalidade(jogador.getNacionalidade());
//		newJogador.setEstado_nasc(jogador.getEstado_nasc());
//		newJogador.setMunicipio_nasc(jogador.getMunicipio_nasc());
//		newJogador.setSexo(jogador.getSexo());
//		newJogador.setAltura(jogador.getAltura());
//		newJogador.setPeso(jogador.getPeso());
//		newJogador.setProfissionalizacao(jogador.getProfissionalizacao());
//		newJogador.setCodigo_cbf(jogador.getCodigo_cbf());
//		//modalidade
//		//posições
//		newJogador.setPerna_preferida(jogador.getPerna_preferida());
//		newJogador.setPrefixo_fone(jogador.getPrefixo_fone());
//		newJogador.setDdd_fone(jogador.getDdd_fone());
//		newJogador.setFone(jogador.getFone());
//		newJogador.setEmail(jogador.getEmail());
		//lances
//		newJogador.setComplemento(jogador.getComplemento());
//		newJogador.setClubeFutebol(jogador.getClubeFutebol());
//		newJogador.setEmpresario(jogador.getEmpresario());
		//lista observacao
		//lista historico
//		newJogador.setUsuario(jogador.getUsuario());
	}
	
}
