package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucasloose.appfooturestars.domain.enums.Perfil;
import com.lucasloose.appfooturestars.domain.enums.TipoUsuario;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	
	@JsonIgnore
	private String senha;
	
	private Integer tipoUsuario;
	
//	@JsonBackReference
	@JsonIgnore
	@OneToOne(mappedBy="usuario")
	private ClubeFutebol clubeFutebol;
	
//	@JsonBackReference
	@JsonIgnore
	@OneToOne(mappedBy="usuario")
	private Empresario empresario;
	
////	@JsonBackReference
	@JsonIgnore
////	@OneToOne(cascade=CascadeType.ALL, mappedBy="usuario")
	@OneToOne(mappedBy="usuario")
	private Jogador jogador;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	
	public Usuario() {
		addPerfil(Perfil.ADMIN);
	}
	
	public Usuario(String email, String senha, TipoUsuario tipoUsuario) {
		super();
		this.email = email;
		this.senha = senha;
//		this.nome = nome;
		this.tipoUsuario = (tipoUsuario==null) ? null : tipoUsuario.getId();
//		this.clubeFutebol = clubeFutebol;
//		this.empresario = empresario;
//		this.jogador = jogador;
		addPerfil(Perfil.ADMIN);
	}
	
	public Usuario(String email, String senha, TipoUsuario tipoUsuario, ClubeFutebol clubeFutebol,
			Empresario empresario, Jogador jogador) {
		super();
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = (tipoUsuario==null) ? null : tipoUsuario.getId();
		this.clubeFutebol = clubeFutebol;
		this.empresario = empresario;
		this.jogador = jogador;
		addPerfil(Perfil.ADMIN);
	}
	
	public Usuario (String email) {
		this.email = email;
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
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
//		result = prime * result + ((jogador == null) ? 0 : jogador.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
//		if (jogador == null) {
//			if (other.jogador != null)
//				return false;
//		} else if (!jogador.equals(other.jogador))
//			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
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
