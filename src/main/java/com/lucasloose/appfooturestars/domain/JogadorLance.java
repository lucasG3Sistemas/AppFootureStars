package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class JogadorLance implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//DADOS PESSOAIS
	private Integer id;
	private byte lance;
	private String pais_atual;
	private String estado_atual;
	private String municipio_atual;
	private String complemento;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "id_jogador_lance")
	private Jogador jogador;
	
	
	public JogadorLance() {
		
	}

	public JogadorLance(Integer id, byte lance, String pais_atual, String estado_atual, String municipio_atual,
			String complemento, Jogador jogador) {
		super();
		this.id = id;
		this.lance = lance;
		this.pais_atual = pais_atual;
		this.estado_atual = estado_atual;
		this.municipio_atual = municipio_atual;
		this.complemento = complemento;
		this.jogador = jogador;
	}
	
	public JogadorLance(Integer id, String pais_atual, String estado_atual, String municipio_atual, Jogador jogador) {
		super();
		this.id = id;
		this.pais_atual = pais_atual;
		this.estado_atual = estado_atual;
		this.municipio_atual = municipio_atual;
		this.jogador = jogador;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getLance() {
		return lance;
	}

	public void setLance(byte lance) {
		this.lance = lance;
	}

	public String getPais_atual() {
		return pais_atual;
	}

	public void setPais_atual(String pais_atual) {
		this.pais_atual = pais_atual;
	}

	public String getEstado_atual() {
		return estado_atual;
	}

	public void setEstado_atual(String estado_atual) {
		this.estado_atual = estado_atual;
	}

	public String getMunicipio_atual() {
		return municipio_atual;
	}

	public void setMunicipio_atual(String municipio_atual) {
		this.municipio_atual = municipio_atual;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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
		JogadorLance other = (JogadorLance) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}