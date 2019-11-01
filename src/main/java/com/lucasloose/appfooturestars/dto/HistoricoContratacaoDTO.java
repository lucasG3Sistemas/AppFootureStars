package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;
import java.util.Date;

public class HistoricoContratacaoDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idJogador;
	private Integer idClubeFutebol;
	private Date data_contratacao;
	private String msg_clube;
	private String msg_jogador;
	private String complemento;
	
	
	public HistoricoContratacaoDTO() {
		
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

	public Date getData_contratacao() {
		return data_contratacao;
	}


	public void setData_contratacao(Date data_contratacao) {
		this.data_contratacao = data_contratacao;
	}


	public String getMsg_clube() {
		return msg_clube;
	}


	public void setMsg_clube(String msg_clube) {
		this.msg_clube = msg_clube;
	}


	public String getMsg_jogador() {
		return msg_jogador;
	}


	public void setMsg_jogador(String msg_jogador) {
		this.msg_jogador = msg_jogador;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
