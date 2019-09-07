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
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ModalidadePosicao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String descricao;
	
//	@JsonBackReference
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "POSICAO_MODALIDADE",
		joinColumns = @JoinColumn(name = "id_posicao"),
		inverseJoinColumns = @JoinColumn(name = "id_modalidade")
	)
	private List<Modalidade> modalidades = new ArrayList<Modalidade>();
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_modalidade")
	private Modalidade modalidade;
	
	
//	@JsonBackReference
	@JsonIgnore
	@ManyToMany(mappedBy="posicoes")
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	
	
	public ModalidadePosicao() {
		
	}

	public ModalidadePosicao(Integer id, String descricao) {
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

	public List<Modalidade> getModalidades() {
		return modalidades;
	}

	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
	}
	
	

	
//	public List<Jogador> getjogadores() {
//		return jogadores;
//	}
//
//	public void setjogadores(List<Jogador> jogadores) {
//		this.jogadores = jogadores;
//	}

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
		ModalidadePosicao other = (ModalidadePosicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
