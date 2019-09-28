package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ClubeFutebol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private byte foto;
	
//	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "CLUBE_MODALIDADE",
		joinColumns = @JoinColumn(name = "id_modalidade"),
		inverseJoinColumns = @JoinColumn(name = "id_clube_futebol")
	)
	private List<Modalidade> modalidades = new ArrayList<Modalidade>();
	
	private Integer profissionalizacao;
	private String registro_cbf;
	private String pais;
	private String estado;
	private String municipio;
	
	//DADOS CONTATO
	private String email;
	
	private String complemento;
	
////	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy="clubeFutebol")
	private List<Jogador> jogadores = new ArrayList<Jogador>();;
	
//	@JsonBackReference
//	@JsonIgnore
//	@OneToOne
//	@OneToMany(mappedBy="clubeFutebol")
//	private ListaObservacao listaObservacao;
	
//	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy="clubeFutebol")
	private List<HistoricoContratacao> historicoContratacoes = new ArrayList<HistoricoContratacao>();;
	
//	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	
	public ClubeFutebol() {
		
	}

	public ClubeFutebol(Integer id, String nome, 
			byte foto,
//			Modalidade modalidade,
			Integer profissionalizacao,
		String registro_cbf, String pais, String estado, String municipio, String email, String complemento, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.foto = foto;
//		this.modalidade = modalidade;
		this.profissionalizacao = profissionalizacao;
		this.registro_cbf = registro_cbf;
		this.pais = pais;
		this.estado = estado;
		this.municipio = municipio;
		this.email = email;
		this.complemento = complemento;
		this.usuario = usuario;
	}
	
	public ClubeFutebol(Integer id, String nome, 
//			byte foto,
//			Modalidade modalidade,
			Integer profissionalizacao,
		String registro_cbf, String pais, String estado, String municipio, String email, String complemento) {
		super();
		this.id = id;
		this.nome = nome;
//		this.foto = foto;
//		this.modalidade = modalidade;
		this.profissionalizacao = profissionalizacao;
		this.registro_cbf = registro_cbf;
		this.pais = pais;
		this.estado = estado;
		this.municipio = municipio;
		this.email = email;
		this.complemento = complemento;
	}
	
	public ClubeFutebol(Integer id) {
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

	public byte getFoto() {
		return foto;
	}

	public void setFoto(byte foto) {
		this.foto = foto;
	}

	public List<Modalidade> getModalidades() {
		return modalidades;
	}

	public void setModalidades(List<Modalidade> modalidades) {
		this.modalidades = modalidades;
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

//	public List<Jogador> getJogadores() {
//		return jogadores;
//	}
//
//	public void setJogadores(List<Jogador> jogadores) {
//		this.jogadores = jogadores;
//	}
//
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
		ClubeFutebol other = (ClubeFutebol) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
