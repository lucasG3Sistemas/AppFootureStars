package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasloose.appfooturestars.domain.Empresario;
import com.lucasloose.appfooturestars.services.validation.EmpresarioUpdate;

@EmpresarioUpdate
public class EmpresarioDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nome;
	
	private byte foto;
	
	@NotEmpty(message = "Campo obrigatório")
	@CPF
	private String cpf;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;
	
	private String nacionalidade;
	
	private String estado_nasc;
	
	private String municipio_nasc;
	
	private Integer sexo;
	
	//DADOS CONTATO
	private Integer prefixo_fone;
	
	private Integer ddd_fone;
	
	private Integer fone;
	
	@NotEmpty(message = "Campo obrigatório")
	@Email
	private String email;
	
	private String complemento;
	
	private String idUsuario;
	
	
	public EmpresarioDTO() {
		
	}

	public EmpresarioDTO(Empresario empresario) {
		this.id = empresario.getId();
		this.nome = empresario.getNome();
		this.foto = empresario.getFoto();
		this.cpf = empresario.getCpf();
		this.data_nasc = empresario.getData_nasc();
		this.nacionalidade = empresario.getNacionalidade();
		this.estado_nasc = empresario.getEstado_nasc();
		this.municipio_nasc = empresario.getMunicipio_nasc();
		this.sexo = empresario.getSexo();
		this.prefixo_fone = empresario.getPrefixo_fone();
		this.ddd_fone = empresario.getDdd_fone();
		this.fone = empresario.getFone();
		this.email = empresario.getEmail();
		this.complemento = empresario.getComplemento();
		this.idUsuario = empresario.getUsuario().getLogin();
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

	public byte getFoto() {
		return foto;
	}

	public void setFoto(byte foto) {
		this.foto = foto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getEstado_nasc() {
		return estado_nasc;
	}

	public void setEstado_nasc(String estado_nasc) {
		this.estado_nasc = estado_nasc;
	}

	public String getMunicipio_nasc() {
		return municipio_nasc;
	}

	public void setMunicipio_nasc(String municipio_nasc) {
		this.municipio_nasc = municipio_nasc;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Integer getPrefixo_fone() {
		return prefixo_fone;
	}

	public void setPrefixo_fone(Integer prefixo_fone) {
		this.prefixo_fone = prefixo_fone;
	}

	public Integer getDdd_fone() {
		return ddd_fone;
	}

	public void setDdd_fone(Integer ddd_fone) {
		this.ddd_fone = ddd_fone;
	}

	public Integer getFone() {
		return fone;
	}

	public void setFone(Integer fone) {
		this.fone = fone;
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
	
}
