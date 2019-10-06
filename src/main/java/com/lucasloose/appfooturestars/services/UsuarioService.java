package com.lucasloose.appfooturestars.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.domain.enums.TipoUsuario;
import com.lucasloose.appfooturestars.dto.UsuarioNewDTO;
import com.lucasloose.appfooturestars.repositories.UsuarioRepository;
import com.lucasloose.appfooturestars.services.exceptions.DataIntegrityException;
import com.lucasloose.appfooturestars.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public Usuario find(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Usuário não encontrado! Login: " + usuario
					+ ", Tipo: " + Usuario.class.getName());
		}
	
		return usuario;
	}
	
	public Usuario fromDTO(UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario = new Usuario(usuarioNewDTO.getEmail(), bCryptPasswordEncoder.encode(usuarioNewDTO.getSenha()), usuarioNewDTO.getNome(), TipoUsuario.toEnum(usuarioNewDTO.getTipoUsuario()));		
		return usuario;
	}
	
	@Transactional
	public Usuario insert(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public void delete(String email) {
		usuarioRepository.findByEmail(email);
		try {
			usuarioRepository.delete(email);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o Usuário");
		}
	}
		
}
