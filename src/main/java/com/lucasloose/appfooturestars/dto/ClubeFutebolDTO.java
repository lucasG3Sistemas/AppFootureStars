package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.lucasloose.appfooturestars.domain.ClubeFutebol;
import com.lucasloose.appfooturestars.services.validation.ClubeFutebolUpdate;

@ClubeFutebolUpdate
public class ClubeFutebolDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nome;
	
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
	
	
	public ClubeFutebolDTO() {
		
	}

	public ClubeFutebolDTO(ClubeFutebol clubeFutebol) {
		this.id = clubeFutebol.getId();
		this.nome = clubeFutebol.getNome();
		this.profissionalizacao = clubeFutebol.getProfissionalizacao();
		this.registro_cbf = clubeFutebol.getRegistro_cbf();
		this.pais = clubeFutebol.getPais();
		this.estado = clubeFutebol.getEstado();
		this.municipio = clubeFutebol.getMunicipio();
		this.email = clubeFutebol.getEmail();
		this.complemento = clubeFutebol.getComplemento();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
