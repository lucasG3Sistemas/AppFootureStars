package com.lucasloose.appfooturestars.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasloose.appfooturestars.domain.Usuario;
import com.lucasloose.appfooturestars.repositories.UsuarioRepository;
import com.lucasloose.appfooturestars.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Integer id = 0;
		Integer tipoUsuario = 0;
		Usuario usu = usuarioRepository.findByLogin(login);
		if (usu == null) {
			throw new UsernameNotFoundException(login);
		}
		
		if (usu.getJogador() != null) {
			id = usu.getJogador().getId();
			tipoUsuario = 1;
		} else if (usu.getEmpresario() != null) {
			id = usu.getEmpresario().getId();
			tipoUsuario = 3;
		} else if (usu.getClubeFutebol() != null) {
			id = usu.getClubeFutebol().getId();
			tipoUsuario = 2;
		} 
		return new UserSS(id, usu.getLogin(), usu.getSenha(), tipoUsuario, usu.getPerfis());
	}

}
