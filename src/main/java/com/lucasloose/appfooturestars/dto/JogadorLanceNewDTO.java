package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;
import java.util.Date;


public class JogadorLanceNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	
	private String idUsuario;
	
	
	public JogadorLanceNewDTO() {
		
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

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
