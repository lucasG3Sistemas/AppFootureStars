package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import com.lucasloose.appfooturestars.domain.ListaObservacao;

public class ListaObservacaoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idJogador;
	private String idUsuario;
//	private Integer idClubeFutebol;
//	private Integer idEmpresario;
	
	
	public ListaObservacaoDTO() {
		
	}

	public ListaObservacaoDTO(ListaObservacao listaObservacao) {
		this.id = listaObservacao.getId();
//		this.idClubeFutebol = listaObservacao.getClubeFutebol().getId();
//		this.idEmpresario = listaObservacao.getEmpresario().getId();
	}
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
//	public Integer getIdClubeFutebol() {
//		return idClubeFutebol;
//	}
//
//	public void setIdClubeFutebol(Integer idClubeFutebol) {
//		this.idClubeFutebol = idClubeFutebol;
//	}
//
//	public Integer getIdEmpresario() {
//		return idEmpresario;
//	}
//
//	public void setIdEmpresario(Integer idEmpresario) {
//		this.idEmpresario = idEmpresario;
//	}

}
