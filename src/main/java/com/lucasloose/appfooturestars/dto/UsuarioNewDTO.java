package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import javax.persistence.Column;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.lucasloose.appfooturestars.services.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Campo obrigatório")
	@Column(unique=true)
	@Email(message = "E-mail inválido")
	private String email;
	
	@NotEmpty(message = "Campo obrigatório")
	private String senha;
	
	private Integer tipoUsuario;
	
	
	public UsuarioNewDTO() {
		
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
