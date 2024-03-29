package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Empresario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//DADOS PESSOAIS
	private Integer id;
	private String nome;
	private String cpf;
	
	//@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;
	
	private String nacionalidade;
	private String estado_nasc;
	private String municipio_nasc;
	
	private Integer sexo;
	
	//DADOS CONTATO
	private Integer prefixo_fone;
	private Integer ddd_fone;
	private Integer fone;
//	private String email;
	
	private String complemento;
	
//	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy="empresario")
	private List<Jogador> jogadores = new ArrayList<Jogador>();;
	
//	@JsonBackReference
//	@JsonIgnore
//	@OneToOne
//	@OneToMany(mappedBy="empresario")
//	private ListaObservacao listaObservacao;
	
//	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	
	public Empresario() {
		
	}

	public Empresario(Integer id, String nome, String cpf, Date data_nasc, String nacionalidade,
			String estado_nasc, String municipio_nasc, Integer sexo, Integer prefixo_fone, Integer ddd_fone, Integer fone,
//			String email,
			String complemento, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.data_nasc = data_nasc;
		this.nacionalidade = nacionalidade;
		this.estado_nasc = estado_nasc;
		this.municipio_nasc = municipio_nasc;
		this.sexo = sexo;
		this.prefixo_fone = prefixo_fone;
		this.ddd_fone = ddd_fone;
		this.fone = fone;
//		this.email = email;
		this.complemento = complemento;
		this.usuario = usuario;
	}
	
	public Empresario(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
//	public Empresario(Integer id, String nome, String email) {
//		super();
//		this.id = id;
//		this.nome = nome;
////		this.email = email;
//	}
	
	public Empresario(Integer id) {
		super();
		this.id = id;
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

//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
//	public List<Jogador> getJogadores() {
//		return jogadores;
//	}
//
//	public void setJogadores(List<Jogador> jogadores) {
//		this.jogadores = jogadores;
//	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Empresario other = (Empresario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
