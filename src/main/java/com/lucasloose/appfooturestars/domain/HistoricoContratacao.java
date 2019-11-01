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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class HistoricoContratacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
//	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_jogador")
	private Jogador jogador;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_clube_futebol")
	private ClubeFutebol clubeFutebol;
	
	private String imageUrl;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date data_contratacao;
	
	private String msg_clube;
	private String msg_jogador;
	private String complemento;
	
	
	public HistoricoContratacao() {
		
	}


	public HistoricoContratacao(Integer id, 
			Jogador jogador, ClubeFutebol clubeFutebol, Date data_contratacao, String msg_clube, String msg_jogador,
			String complemento) {
		super();
		this.id = id;
		this.jogador = jogador;
		this.clubeFutebol = clubeFutebol;
		this.data_contratacao = data_contratacao;
		this.msg_clube = msg_clube;
		this.msg_jogador = msg_jogador;
		this.complemento = complemento;
	}
	
	public HistoricoContratacao(Integer id, Jogador jogador, ClubeFutebol clubeFutebol, String msg_clube, String msg_jogador,
			String complemento) {
		super();
		this.id = id;
		this.jogador = jogador;
		this.clubeFutebol = clubeFutebol;
		this.msg_clube = msg_clube;
		this.msg_jogador = msg_jogador;
		this.complemento = complemento;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public ClubeFutebol getClubeFutebol() {
		return clubeFutebol;
	}

	public void setClubeFutebol(ClubeFutebol clubeFutebol) {
		this.clubeFutebol = clubeFutebol;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
		HistoricoContratacao other = (HistoricoContratacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
