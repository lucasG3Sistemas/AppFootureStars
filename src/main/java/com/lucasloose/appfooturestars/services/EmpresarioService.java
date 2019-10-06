package com.lucasloose.appfooturestars.services;

import java.awt.image.BufferedImage;
import java.net.URI;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.domain.enums.Perfil;
import com.lucasloose.appfooturestars.dto.EmpresarioDTO;
import com.lucasloose.appfooturestars.dto.EmpresarioNewDTO;
import com.lucasloose.appfooturestars.repositories.EmpresarioRepository;
import com.lucasloose.appfooturestars.security.UserSS;
import com.lucasloose.appfooturestars.services.exceptions.AuthorizationException;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class EmpresarioService {

	@Autowired
	private EmpresarioRepository empresarioRepository;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.empresario.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	

	public Empresario find(Integer id) {
		Empresario empresario = empresarioRepository.findOne(id);
		if (empresario == null) {
			throw new ObjectNotFoundException(
					"Empresário não encontrado! ID: " + id + ", Tipo: " + Empresario.class.getName());
		}
		return empresario;
	}

	public Empresario findByEmail(String email) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}

		Empresario obj = empresarioRepository.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Empresario.class.getName());
		}
		return obj;
	}
	
	public Empresario fromDTO(EmpresarioDTO empresarioDTO) {
		// passei a data como null
		Usuario usuario = new Usuario(empresarioDTO.getIdUsuario());
		Empresario empresario = new Empresario(null, empresarioDTO.getNome(), empresarioDTO.getCpf(), null,
				empresarioDTO.getNacionalidade(), empresarioDTO.getEstado_nasc(), empresarioDTO.getMunicipio_nasc(),
				empresarioDTO.getSexo(), empresarioDTO.getPrefixo_fone(), empresarioDTO.getDdd_fone(),
				empresarioDTO.getFone(), empresarioDTO.getEmail(), empresarioDTO.getComplemento(), usuario);

		return empresario;
	}

	public Empresario fromDTO(EmpresarioNewDTO empresarioNewDTO) {
		// passei a data como null
		Usuario usuario = new Usuario(empresarioNewDTO.getIdUsuario());
		Empresario empresario = new Empresario(null, empresarioNewDTO.getNome(), empresarioNewDTO.getCpf(), null,
				empresarioNewDTO.getNacionalidade(), empresarioNewDTO.getEstado_nasc(),
				empresarioNewDTO.getMunicipio_nasc(), empresarioNewDTO.getSexo(), empresarioNewDTO.getPrefixo_fone(),
				empresarioNewDTO.getDdd_fone(), empresarioNewDTO.getFone(), empresarioNewDTO.getEmail(),
				empresarioNewDTO.getComplemento(), usuario);

		return empresario;
	}

	@Transactional
	public Empresario insert(Empresario empresario) {
		empresario.setId(null);
		return empresarioRepository.save(empresario);
	}

	public Empresario update(Empresario empresario) {
		Empresario newEmpresario = this.find(empresario.getId());
		this.updateData(newEmpresario, empresario);
		return empresarioRepository.save(newEmpresario);
	}

	public void delete(Integer id) {
		this.find(id);
		try {
			empresarioRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um EMPRESARIO pq há entidades relacionadas");
		}
	}

	private void updateData(Empresario newEmpresario, Empresario empresario) {
		newEmpresario.setNome(empresario.getNome());
		newEmpresario.setCpf(empresario.getCpf() != null ? empresario.getCpf() : newEmpresario.getCpf());
		newEmpresario.setData_nasc(empresario.getData_nasc());
		newEmpresario.setNacionalidade(empresario.getNacionalidade());
		newEmpresario.setEstado_nasc(empresario.getEstado_nasc());
		newEmpresario.setMunicipio_nasc(empresario.getMunicipio_nasc());
		newEmpresario.setSexo(empresario.getSexo());
		newEmpresario.setPrefixo_fone(empresario.getPrefixo_fone());
		newEmpresario.setDdd_fone(empresario.getDdd_fone());
		newEmpresario.setFone(empresario.getFone());
		newEmpresario.setEmail(empresario.getEmail());
		newEmpresario.setComplemento(empresario.getComplemento());
		newEmpresario.setUsuario(empresario.getUsuario());
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
