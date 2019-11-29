package com.lucasloose.appfooturestars.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Jogador;
import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.ModalidadePosicao;
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.domain.enums.Perfil;
import com.lucasloose.appfooturestars.dto.JogadorDTO;
import com.lucasloose.appfooturestars.dto.JogadorNewDTO;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.repositories.JogadorRepository;
import com.lucasloose.appfooturestars.repositories.ModalidadePosicaoRepository;
import com.lucasloose.appfooturestars.repositories.UsuarioRepository;
import com.lucasloose.appfooturestars.security.UserSS;
import com.lucasloose.appfooturestars.services.exceptions.AuthorizationException;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;
import com.lucasloose.appfooturestars.utils.ConverteUF;

@Service
public class JogadorService {

	@Autowired
	private JogadorRepository jogadorRepository;

	@Autowired
	private ModalidadePosicaoRepository posicaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;
	
	@Autowired
	private EmpresarioRepository empresarioRepository;
	
	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ConverteUF converteUF;
	
	@Value("${img.profile.size}")
	private Integer size;
	

	@Value("${img.prefix.jogador.profile}")
	private String prefix;

	
	
	public List<Jogador> searchNome(String idLista, String usuario, String nome) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !usuario.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		List<Jogador> lista = null;
		Usuario usu = new Usuario(usuario);
		ClubeFutebol clube = clubeFutebolRepository.findByUsuario(usu);
		if (clube != null) {
			if (!idLista.equals("")) {				
				lista = jogadorRepository.listaJogadoresClubeFutebolPorNome(Integer.parseInt(idLista), clube.getId(), nome);
			} else {
				lista = jogadorRepository.jogadoresClubeFutebolPorNome(clube.getId(), nome);
			}
		} else {
			Empresario empr = empresarioRepository.findByUsuario(usu);
			if (empr != null) {
				if (!idLista.equals("")) {
					lista = jogadorRepository.listaJogadoresEmpresarioPorNome(Integer.parseInt(idLista), empr.getId(), nome);
				} else {
					lista = jogadorRepository.jogadoresEmpresarioPorNome(empr.getId(), nome);
				}
			}
		}
		
		if (lista == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Jogador.class.getName());
		}
		return lista;
		
	}
	
	public Page<Jogador> search(String nome, List<Integer> idsPosicoes, Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		// busca jogadores a partir de uma posição
		List<ModalidadePosicao> posicoes = posicaoRepository.findAll(idsPosicoes);
		return jogadorRepository.findDistinctByNomeContainingAndPosicoesIn(nome, posicoes, pageRequest);
	}

	public List<Jogador> findAll() {
		List<Jogador> jogador = jogadorRepository.findAll();
		if (jogador == null) {
			throw new ObjectNotFoundException("Jogadores não encontrados!" + ", Tipo: " + Jogador.class.getName());
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
			throw new ObjectNotFoundException(
					"Jogador não encontrado! ID: " + id + ", Tipo: " + Jogador.class.getName());
		}

		return jogador;
	}

	public Page<Jogador> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return jogadorRepository.findAll(pageRequest);
	}
	
	public Jogador findByEmail(String email) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Usuario usu = usuarioRepository.findOne(email);
		Jogador obj = jogadorRepository.findByUsuario(usu);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Jogador.class.getName());
		}
		return obj;
	}
	
	public List<Jogador> findIdListaObservacao(String idLista, String usuario) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !usuario.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		List<Jogador> lista = null;
		Usuario usu = new Usuario(usuario);
		ClubeFutebol clube = clubeFutebolRepository.findByUsuario(usu);
		if (clube != null) {
			if (!idLista.equals("")) {
				lista = jogadorRepository.listaJogadoresClubeFutebol(Integer.parseInt(idLista), clube.getId());
			} else {
				lista = jogadorRepository.listaJogadoresClubeFutebol(clube.getId());
			}
		} else {
			Empresario empr = empresarioRepository.findByUsuario(usu);
			if (empr != null) {
				if (!idLista.equals("")) {
					lista = jogadorRepository.listaJogadoresEmpresario(Integer.parseInt(idLista), empr.getId());
				} else {
					lista = jogadorRepository.listaJogadoresEmpresario(empr.getId());
				}
			}
		}
		
		if (lista == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Jogador.class.getName());
		}
		return lista;
		
	}
	
	public List<Jogador> findByJogadoresUsuario(String usuario) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !usuario.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		List<Jogador> lista = null;
		Usuario usu = new Usuario(usuario);
		ClubeFutebol clube = clubeFutebolRepository.findByUsuario(usu);
		if (clube != null) {
			lista = jogadorRepository.findByClubeFutebol(clube);
		} else {
			Empresario empr = empresarioRepository.findByUsuario(usu);
			if (empr != null) {
				lista = jogadorRepository.findByEmpresario(empr);
			}
		}
		
		if (lista == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Jogador.class.getName());
		}
		return lista;
	}
	

	public Jogador fromDTO(JogadorDTO jogadorDTO) {
		
//		Modalidade modalidade = new Modalidade(jogadorDTO.getIdModalidade(), "");
//		ClubeFutebol clube = new ClubeFutebol(jogadorDTO.getIdClubeFutebol());
//		Empresario empresario = new Empresario(jogadorDTO.getIdEmpresario());
//		Usuario usuario = new Usuario(jogadorDTO.getIdUsuario());
		Modalidade modalidade = new Modalidade(jogadorDTO.getIdModalidade(), "");
		
		Jogador jogador = this.find(jogadorDTO.getId());
		jogador.setAltura(jogadorDTO.getAltura());
		jogador.setPeso(jogadorDTO.getPeso());
		jogador.setProfissionalizacao(jogadorDTO.getProfissionalizacao());
		jogador.setCodigo_cbf(jogadorDTO.getCodigo_cbf());
		jogador.setModalidade(modalidade);
		jogador.setPerna_preferida(jogadorDTO.getPerna_preferida());
		jogador.setPrefixo_fone(jogadorDTO.getPrefixo_fone());
		jogador.setDdd_fone(jogadorDTO.getDdd_fone());
		jogador.setFone(jogadorDTO.getFone());
		jogador.setComplemento(jogadorDTO.getComplemento());
//		jogador = new Jogador(jogadorDTO.getId(), 
//				jogadorDTO.getCpf(), 
//				null,
//				jogadorDTO.getNacionalidade(), jogadorDTO.getEstado_nasc(), jogadorDTO.getMunicipio_nasc(),
//				jogadorDTO.getSexo(), 
//				jogadorDTO.getAltura(), jogadorDTO.getPeso(), jogadorDTO.getProfissionalizacao(),
//				jogadorDTO.getCodigo_cbf(), 
//				modalidade, 
//				jogadorDTO.getPerna_preferida(), 
//				jogadorDTO.getPrefixo_fone(),
//				jogadorDTO.getDdd_fone(), jogadorDTO.getFone(), 
//				jogadorDTO.getEmail(), 
//				jogadorDTO.getComplemento()
//				clube, empresario, usuario
//				);
//		jogador.getModalidade().setId(jogadorDTO.getIdModalidade());
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
		Modalidade modalidade = new Modalidade(jogadorNewDTO.getIdModalidade(), "");
		
		String uf = converteUF.converteCodToUF(Integer.parseInt(jogadorNewDTO.getEstado_nasc()));
		
		Jogador jogador = new Jogador(null, jogadorNewDTO.getNome(), jogadorNewDTO.getCpf(), jogadorNewDTO.getData_nasc(), jogadorNewDTO.getNacionalidade(),
			uf, jogadorNewDTO.getMunicipio_nasc(), jogadorNewDTO.getSexo(), jogadorNewDTO.getAltura(),
			jogadorNewDTO.getPeso(), jogadorNewDTO.getProfissionalizacao(),
			jogadorNewDTO.getCodigo_cbf(), modalidade, jogadorNewDTO.getPerna_preferida(), jogadorNewDTO.getPrefixo_fone(), jogadorNewDTO.getDdd_fone(), jogadorNewDTO.getFone(),
			jogadorNewDTO.getEmail(), jogadorNewDTO.getComplemento());
		
		Usuario usu = new Usuario(jogadorNewDTO.getIdUsuario());
		ClubeFutebol clube = clubeFutebolRepository.findByUsuario(usu);
		if (clube != null) {
			jogador.setClubeFutebol(clube);
			usu = null;
		} else {
			Empresario empr = empresarioRepository.findByUsuario(usu);
			if (empr != null) {
				jogador.setEmpresario(empr);
				usu = null;
			}
		}
		
		
		
		
//		if (jogadorNewDTO.getIdClubeFutebol() != null) {
//			ClubeFutebol clube = new ClubeFutebol(jogadorNewDTO.getIdClubeFutebol());
//			jogador.setClubeFutebol(clube);
//		}
//		
//		if (jogadorNewDTO.getIdEmpresario() != null) {
//			Empresario empresario = new Empresario(jogadorNewDTO.getIdEmpresario());
//			jogador.setEmpresario(empresario);
//		}
		
		if (jogadorNewDTO.getIdUsuario() != null && usu != null) {
//			Usuario usuario = new Usuario(jogadorNewDTO.getIdUsuario());
			jogador.setUsuario(usu);
		}
		
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
			for (int i = 0; i < jogador.getPosicoes().size(); i++) {
				newJogador.getPosicoes().add(jogador.getPosicoes().get(i));
			}
		}
		newJogador.setPerna_preferida(jogador.getPerna_preferida());
		newJogador.setPrefixo_fone(jogador.getPrefixo_fone());
		newJogador.setDdd_fone(jogador.getDdd_fone());
		newJogador.setFone(jogador.getFone());
		newJogador.setEmail(jogador.getEmail());
		// lances
		newJogador.setComplemento(jogador.getComplemento());
		newJogador.setClubeFutebol(jogador.getClubeFutebol());
		newJogador.setEmpresario(jogador.getEmpresario());
		// lista observacao
		// lista historico
		newJogador.setUsuario(jogador.getUsuario());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

}
