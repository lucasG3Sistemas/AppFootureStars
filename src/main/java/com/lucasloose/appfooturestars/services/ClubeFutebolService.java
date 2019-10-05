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
import com.lucasloose.appfooturestars.domain.Modalidade;
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.dto.ClubeFutebolDTO;
import com.lucasloose.appfooturestars.dto.ClubeFutebolNewDTO;
import com.lucasloose.appfooturestars.repositories.ClubeFutebolRepository;
import com.lucasloose.appfooturestars.security.UserSS;
import com.lucasloose.appfooturestars.services.exceptions.AuthorizationException;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class ClubeFutebolService {

	@Autowired
	private ClubeFutebolRepository clubeFutebolRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.clube.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	

	public List<ClubeFutebol> findAll() {
		List<ClubeFutebol> clubeFutebol = clubeFutebolRepository.findAll();
		if (clubeFutebol == null) {
			throw new ObjectNotFoundException(
					"Clubes de Futebol não encontrados!" + ", Tipo: " + ClubeFutebol.class.getName());
		}
		return clubeFutebol;
	}

	public ClubeFutebol find(Integer id) {
		ClubeFutebol clubeFutebol = clubeFutebolRepository.findOne(id);
		if (clubeFutebol == null) {
			throw new ObjectNotFoundException(
					"Clube de Futebol não encontrado! ID: " + id + ", Tipo: " + ClubeFutebol.class.getName());
		}
		return clubeFutebol;
	}

	public Page<ClubeFutebol> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clubeFutebolRepository.findAll(pageRequest);
	}

	public ClubeFutebol fromDTO(ClubeFutebolDTO clubeFutebolDTO) {
		Usuario usuario = new Usuario(clubeFutebolDTO.getIdUsuario());
		ClubeFutebol clubeFutebol = new ClubeFutebol(clubeFutebolDTO.getId(), clubeFutebolDTO.getNome(),
				clubeFutebolDTO.getProfissionalizacao(), clubeFutebolDTO.getRegistro_cbf(), clubeFutebolDTO.getPais(),
				clubeFutebolDTO.getEstado(), clubeFutebolDTO.getMunicipio(), clubeFutebolDTO.getEmail(),
				clubeFutebolDTO.getComplemento(), usuario);
		Modalidade mod1 = new Modalidade(clubeFutebolDTO.getIdModalidade1(), "");
		clubeFutebol.getModalidades().add(mod1);
		if (clubeFutebolDTO.getIdModalidade2() != null) {
			Modalidade mod2 = new Modalidade(clubeFutebolDTO.getIdModalidade2(), "");
			clubeFutebol.getModalidades().add(mod2);
		}

		return clubeFutebol;
	}

	public ClubeFutebol fromDTO(ClubeFutebolNewDTO clubeFutebolNewDTO) {
		Usuario usuario = new Usuario(clubeFutebolNewDTO.getIdUsuario());
		ClubeFutebol clubeFutebol = new ClubeFutebol(null, clubeFutebolNewDTO.getNome(),
				clubeFutebolNewDTO.getProfissionalizacao(), clubeFutebolNewDTO.getRegistro_cbf(),
				clubeFutebolNewDTO.getPais(), clubeFutebolNewDTO.getEstado(), clubeFutebolNewDTO.getMunicipio(),
				clubeFutebolNewDTO.getEmail(), clubeFutebolNewDTO.getComplemento(), usuario);
		Modalidade mod1 = new Modalidade(clubeFutebolNewDTO.getIdModalidade1(), "");
		clubeFutebol.getModalidades().add(mod1);
		if (clubeFutebolNewDTO.getIdModalidade2() != null) {
			Modalidade mod2 = new Modalidade(clubeFutebolNewDTO.getIdModalidade2(), "");
			clubeFutebol.getModalidades().add(mod2);
		}
		return clubeFutebol;
	}

	@Transactional
	public ClubeFutebol insert(ClubeFutebol clubeFutebol) {
		clubeFutebol.setId(null);
//		jogador = jogadorRepository.save(jogador);
//		outroRepository.save(obj.getOUTRO());
//		return obj;
		System.out.println(clubeFutebol);
		return clubeFutebolRepository.save(clubeFutebol);
	}

	public ClubeFutebol update(ClubeFutebol clubeFutebol) {
		ClubeFutebol newClubeFutebol = this.find(clubeFutebol.getId());
		this.updateData(newClubeFutebol, clubeFutebol);
		return clubeFutebolRepository.save(newClubeFutebol);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			clubeFutebolRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o Clube pq há entidades relacionadas");
		}
	}

	private void updateData(ClubeFutebol newClubeFutebol, ClubeFutebol clubeFutebol) {

		newClubeFutebol.setNome(clubeFutebol.getNome() != null ? clubeFutebol.getNome() : newClubeFutebol.getNome());
		newClubeFutebol.setProfissionalizacao(
				clubeFutebol.getProfissionalizacao() != null ? clubeFutebol.getProfissionalizacao()
						: newClubeFutebol.getProfissionalizacao());
		newClubeFutebol.setRegistro_cbf(clubeFutebol.getRegistro_cbf() != null ? clubeFutebol.getRegistro_cbf()
				: newClubeFutebol.getRegistro_cbf());
		newClubeFutebol.setPais(clubeFutebol.getPais() != null ? clubeFutebol.getPais() : newClubeFutebol.getPais());
		newClubeFutebol
				.setEstado(clubeFutebol.getEstado() != null ? clubeFutebol.getEstado() : newClubeFutebol.getEstado());
		newClubeFutebol.setMunicipio(
				clubeFutebol.getMunicipio() != null ? clubeFutebol.getMunicipio() : newClubeFutebol.getMunicipio());
		newClubeFutebol
				.setEmail(clubeFutebol.getEmail() != null ? clubeFutebol.getEmail() : newClubeFutebol.getEmail());
		newClubeFutebol.setComplemento(clubeFutebol.getComplemento() != null ? clubeFutebol.getComplemento()
				: newClubeFutebol.getComplemento());
		if (clubeFutebol.getModalidades() != null) {
			newClubeFutebol.getModalidades().removeAll(newClubeFutebol.getModalidades());
			for (int i = 0; i < clubeFutebol.getModalidades().size(); i++) {
				newClubeFutebol.getModalidades().add(clubeFutebol.getModalidades().get(i));
			}
		}
		newClubeFutebol.setUsuario(clubeFutebol.getUsuario());
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
