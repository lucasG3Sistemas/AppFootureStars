package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String login;
	private String senha;
	private String nome;
	private Integer perfil;
	
	@JsonBackReference
	@OneToOne(mappedBy="usuario")
	private ClubeFutebol clubeFutebol;
	
	@JsonBackReference
	@OneToOne(mappedBy="usuario")
	private Empresario empresario;
	
	@JsonBackReference
	@OneToOne(mappedBy="usuario")
	private Jogador jogador;
	
	
	public Usuario() {
		
	}

	public Usuario(String login, String senha, String nome, Integer perfil, ClubeFutebol clubeFutebol,
			Empresario empresario, Jogador jogador) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.perfil = perfil;
		this.clubeFutebol = clubeFutebol;
		this.empresario = empresario;
		this.jogador = jogador;
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

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public ClubeFutebol getClubeFutebol() {
		return clubeFutebol;
	}

	public void setClubeFutebol(ClubeFutebol clubeFutebol) {
		this.clubeFutebol = clubeFutebol;
	}

	public Empresario getEmpresario() {
		return empresario;
	}

	public void setEmpresario(Empresario empresario) {
		this.empresario = empresario;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + perfil;
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (perfil != other.perfil)
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
