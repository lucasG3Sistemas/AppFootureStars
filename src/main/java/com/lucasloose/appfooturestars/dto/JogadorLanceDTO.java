package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;
import java.util.Date;

import com.lucasloose.appfooturestars.domain.JogadorLance;


public class JogadorLanceDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String titulo;
	
	private String urlVideo;
	
	private String descricao;
	
	private Date data_publicacao;
//	
//	private String pais_atual;
//	
//	private String estado_atual;
//	
//	private String municipio_atual;
	
	private String complemento;
	
	private Integer idJogador;
	
	
	public JogadorLanceDTO() {
		
	}

	public JogadorLanceDTO(JogadorLance jogadorLance) {
		this.id = jogadorLance.getId();
		this.titulo = jogadorLance.getTitulo();
		this.urlVideo = jogadorLance.getUrlVideo();
		this.descricao = jogadorLance.getDescricao();
		this.data_publicacao = jogadorLance.getData_publicacao();
//		this.pais_atual = jogadorLance.getPais_atual();
//		this.estado_atual = jogadorLance.getEstado_atual();
//		this.municipio_atual = jogadorLance.getMunicipio_atual();
		this.complemento = jogadorLance.getComplemento();
		this.idJogador = jogadorLance.getJogador().getId();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
//
//	public String getPais_atual() {
//		return pais_atual;
//	}
//
//	public void setPais_atual(String pais_atual) {
//		this.pais_atual = pais_atual;
//	}
//
//	public String getEstado_atual() {
//		return estado_atual;
//	}
//
//	public void setEstado_atual(String estado_atual) {
//		this.estado_atual = estado_atual;
//	}
//
//	public String getMunicipio_atual() {
//		return municipio_atual;
//	}
//
//	public void setMunicipio_atual(String municipio_atual) {
//		this.municipio_atual = municipio_atual;
//	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(Integer idJogador) {
		this.idJogador = idJogador;
	}
	
}
