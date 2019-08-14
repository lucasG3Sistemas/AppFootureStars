package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ListaObservacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "LISTA_OBS_JOGADOR",
		joinColumns = @JoinColumn(name = "id_jogador"),
		inverseJoinColumns = @JoinColumn(name = "id_lista_observacao")
	)
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "id_clube_futebol")
	private ClubeFutebol clubeFutebol;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "id_empresario")
	private Empresario empresario;
	
	
	public ListaObservacao() {
		
	}

	public ListaObservacao(Integer id) {
		super();
		this.id = id;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ListaObservacao other = (ListaObservacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
