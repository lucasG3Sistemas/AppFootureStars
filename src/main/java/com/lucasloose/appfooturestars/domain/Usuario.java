package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lucasloose.appfooturestars.domain.enums.TipoUsuario;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String login;
	private String senha;
	private String nome;
	private Integer tipoUsuario;
	
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

	public Usuario(String login, String senha, String nome, TipoUsuario tipoUsuario, ClubeFutebol clubeFutebol,
			Empresario empresario, Jogador jogador) {
		super();
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario.getId();
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

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario.toEnum(tipoUsuario);
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario.getId();
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
		result = prime * result + ((clubeFutebol == null) ? 0 : clubeFutebol.hashCode());
		result = prime * result + ((empresario == null) ? 0 : empresario.hashCode());
		result = prime * result + ((jogador == null) ? 0 : jogador.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
		if (clubeFutebol == null) {
			if (other.clubeFutebol != null)
				return false;
		} else if (!clubeFutebol.equals(other.clubeFutebol))
			return false;
		if (empresario == null) {
			if (other.empresario != null)
				return false;
		} else if (!empresario.equals(other.empresario))
			return false;
		if (jogador == null) {
			if (other.jogador != null)
				return false;
		} else if (!jogador.equals(other.jogador))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tipoUsuario != other.tipoUsuario)
			return false;
		return true;
	}

}
