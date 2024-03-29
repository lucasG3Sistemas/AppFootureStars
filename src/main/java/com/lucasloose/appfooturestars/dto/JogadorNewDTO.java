package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucasloose.appfooturestars.services.validation.JogadorInsert;

@JogadorInsert
public class JogadorNewDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nome;
	
	@NotEmpty(message = "Campo obrigatório")
	@CPF
	private String cpf;
	
//	@NotEmpty(message = "Campo obrigatório")
//	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;
	
	@NotEmpty(message = "Campo obrigatório")
	private String nacionalidade;
	
	@NotEmpty(message = "Campo obrigatório")
	private String estado_nasc;
	
	@NotEmpty(message = "Campo obrigatório")
	private String municipio_nasc;
	
	private Integer sexo;
	
	private Double altura;
	
	private Double peso;
	
	//DADOS JOGADOR
	private Integer profissionalizacao;
	
	private String codigo_cbf;
	
	private Integer idModalidade;
	
	private Integer idPosicao1;
	private Integer idPosicao2;
	private Integer idPosicao3;
	
	private Integer perna_preferida;
	
	//DADOS CONTATO
	private Integer prefixo_fone;
	
	private Integer ddd_fone;
	
	private Integer fone;
	
	@NotEmpty(message = "Campo obrigatório")
	@Email(message = "E-mail inválido")
	private String email;
	
	private String complemento;
	
	private Integer idClubeFutebol;
	
	private Integer idEmpresario;
	
	private String idUsuario;
	
	
	public JogadorNewDTO() {
		
	}

//	public JogadorNewDTO(Jogador jogador) {
//		this.nome = jogador.getNome();
//		this.foto = jogador.getFoto();
////		this.cpf = jogador.getCpf();
////		this.data_nasc = jogador.getData_nasc();
////		this.nacionalidade = jogador.getNacionalidade();
////		this.estado_nasc = jogador.getEstado_nasc();
////		this.municipio_nasc = jogador.getMunicipio_nasc();
////		this.sexo = jogador.getSexo();
////		this.altura = jogador.getAltura();
////		this.peso = jogador.getPeso();
////		this.profissionalizacao = jogador.getProfissionalizacao();
////		this.codigo_cbf = jogador.getCodigo_cbf();
////		this.perna_preferida = jogador.getPerna_preferida();
////		this.prefixo_fone = jogador.getPrefixo_fone();
////		this.ddd_fone = jogador.getDdd_fone();
////		this.fone = jogador.getFone();
//		this.email = jogador.getEmail();
//		this.complemento = jogador.getComplemento();
//		this.idClubeFutebol = jogador.getClubeFutebol().getId();
//		this.idEmpresario = jogador.getEmpresario().getId();
//		this.idUsuario = jogador.getUsuario().getLogin();
//	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Integer getProfissionalizacao() {
		return profissionalizacao;
	}

	public void setProfissionalizacao(Integer profissionalizacao) {
		this.profissionalizacao = profissionalizacao;
	}

	public String getCodigo_cbf() {
		return codigo_cbf;
	}

	public void setCodigo_cbf(String codigo_cbf) {
		this.codigo_cbf = codigo_cbf;
	}
	
	public Integer getPerna_preferida() {
		return perna_preferida;
	}

	public void setPerna_preferida(Integer perna_preferida) {
		this.perna_preferida = perna_preferida;
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

	public Integer getIdModalidade() {
		return idModalidade;
	}

	public void setIdModalidade(Integer idModalidade) {
		this.idModalidade = idModalidade;
	}

	public Integer getIdPosicao1() {
		return idPosicao1;
	}

	public void setIdPosicao1(Integer idPosicao1) {
		this.idPosicao1 = idPosicao1;
	}

	public Integer getIdPosicao2() {
		return idPosicao2;
	}

	public void setIdPosicao2(Integer idPosicao2) {
		this.idPosicao2 = idPosicao2;
	}

	public Integer getIdPosicao3() {
		return idPosicao3;
	}

	public void setIdPosicao3(Integer idPosicao3) {
		this.idPosicao3 = idPosicao3;
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

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
