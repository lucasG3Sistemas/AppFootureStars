package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Modalidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
//	@JsonManagedReference
	@ManyToMany(mappedBy="modalidades")
	private List<ModalidadePosicao> posicoes = new ArrayList<ModalidadePosicao>();
	
//	@JsonBackReference
	@JsonIgnore
	@ManyToMany(mappedBy="modalidades")
	private List<ClubeFutebol> modalidadeClubes = new ArrayList<ClubeFutebol>();
	
//	@JsonBackReference
//	@JsonIgnore
//	@ManyToMany(mappedBy="modalidades")
//	private List<Jogador> modalidadeJogadores = new ArrayList<Jogador>();
	
	public Modalidade() {
		
	}

	public Modalidade(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public List<ModalidadePosicao> getPosicoes() {
		return posicoes;
	}

	public void setPosicoes(List<ModalidadePosicao> posicoes) {
		this.posicoes = posicoes;
	}
	
	
	
//	public ClubeFutebol getClubeFutebol() {
//		return clubeFutebol;
//	}
//
//	public void setClubeFutebol(ClubeFutebol clubeFutebol) {
//		this.clubeFutebol = clubeFutebol;
//	}

//	public List<ClubeFutebol> getModalidadeClubes() {
//		return modalidadeClubes;
//	}
//
//	public void setModalidadeClubes(List<ClubeFutebol> modalidadeClubes) {
//		this.modalidadeClubes = modalidadeClubes;
//	}
//
//	
//	
//	public List<Jogador> getModalidadeJogadores() {
//		return modalidadeJogadores;
//	}
//
//	public void setModalidadeJogadores(List<Jogador> modalidadeJogadores) {
//		this.modalidadeJogadores = modalidadeJogadores;
//	}

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
		Modalidade other = (Modalidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}