package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

public class ListaObservacaoNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idJogador;
	private Integer idClubeFutebol;
	private Integer idEmpresario;
	
	
	public ListaObservacaoNewDTO() {
		
	}


	public Integer getIdJogador() {
		return idJogador;
	}

	public void setIdJogador(Integer idJogador) {
		this.idJogador = idJogador;
	}

	public Integer getIdClubeFutebol() {
		return idClubeFutebol;
	}

	public void setIdClubeFutebol(Integer idClubeFutebol) {
		this.idClubeFutebol = idClubeFutebol;
	}

	public Integer getIdEmpresario() {
		return idEmpresario;
	}

	public void setIdEmpresario(Integer idEmpresario) {
		this.idEmpresario = idEmpresario;
	}

}
