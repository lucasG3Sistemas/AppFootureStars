package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

import com.lucasloose.appfooturestars.services.validation.UsuarioInsert;

@UsuarioInsert
public class UsuarioNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Campo obrigatório")
	@Column(unique=true)
	private String login;
	
	@NotEmpty(message = "Campo obrigatório")
	private String senha;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nome;
	
	private Integer tipoUsuario;
	
	
	public UsuarioNewDTO() {
		
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
