package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.lucasloose.appfooturestars.services.validation.ClubeFutebolInsert;

@ClubeFutebolInsert
public class ClubeFutebolNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nome;
	
	private byte foto;
	
	@NotEmpty(message = "Campo obrigatório")
	private Integer idModalidade1;
	
	private Integer idModalidade2;
	
	@NotEmpty(message = "Campo obrigatório")
	private Integer profissionalizacao;
	
	private String registro_cbf;
	
	@NotEmpty(message = "Campo obrigatório")
	private String pais;
	
	@NotEmpty(message = "Campo obrigatório")
	private String estado;
	
	@NotEmpty(message = "Campo obrigatório")
	private String municipio;
	
	@NotEmpty(message = "Campo obrigatório")
	@Email
	private String email;
	
	private String complemento;
	
	private String idUsuario;
	
	
	public ClubeFutebolNewDTO() {
		
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte getFoto() {
		return foto;
	}

	public void setFoto(byte foto) {
		this.foto = foto;
	}

	public Integer getIdModalidade1() {
		return idModalidade1;
	}

	public void setIdModalidade1(Integer idModalidade1) {
		this.idModalidade1 = idModalidade1;
	}

	public Integer getIdModalidade2() {
		return idModalidade2;
	}

	public void setIdModalidade2(Integer idModalidade2) {
		this.idModalidade2 = idModalidade2;
	}

	public Integer getProfissionalizacao() {
		return profissionalizacao;
	}

	public void setProfissionalizacao(Integer profissionalizacao) {
		this.profissionalizacao = profissionalizacao;
	}

	public String getRegistro_cbf() {
		return registro_cbf;
	}

	public void setRegistro_cbf(String registro_cbf) {
		this.registro_cbf = registro_cbf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
