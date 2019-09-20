package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class JogadorLance implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//DADOS PESSOAIS
	private Integer id;
	private byte video;
	private String urlVideo;
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date data_publicacao;
	
	private String pais_atual;
	private String estado_atual;
	private String municipio_atual;
	private String complemento;
	
//	@JsonBackReference
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_jogador_lance")
	private Jogador jogador;
	
	
	public JogadorLance() {
		
	}

	public JogadorLance(Integer id, byte video, String urlVideo, String descricao, Date data_publicacao, String pais_atual, String estado_atual, String municipio_atual,
			String complemento, Jogador jogador) {
		super();
		this.id = id;
		this.video = video;
		this.urlVideo = urlVideo;
		this.descricao = descricao;
		this.data_publicacao = data_publicacao;
		this.pais_atual = pais_atual;
		this.estado_atual = estado_atual;
		this.municipio_atual = municipio_atual;
		this.complemento = complemento;
		this.jogador = jogador;
	}
	
	public JogadorLance(Integer id, Date data_publicacao, String pais_atual, String estado_atual, String municipio_atual, Jogador jogador) {
		super();
		this.id = id;
		this.data_publicacao = data_publicacao;
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

	public byte getVideo() {
		return video;
	}

	public void setVideo(byte video) {
		this.video = video;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_publicacao() {
		return data_publicacao;
	}

	public void setData_publicacao(Date data_publicacao) {
		this.data_publicacao = data_publicacao;
	}

	public Date getDataPublicacao() {
		return data_publicacao;
	}

	public void setDataPublicacao(Date data_publicacao) {
		this.data_publicacao = data_publicacao;
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
