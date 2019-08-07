package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Jogador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//DADOS PESSOAIS
	private Integer id;
	private String nome;
	private byte foto;
	private String cpf;
	private LocalDate data_nasc;
	private String nacionalidade;
	private String estado_nasc;
	private String municipio_nasc;
	private Double altura;
	private Double peso;
	
	//DADOS JOGADOR
	private Integer profissionalizacao;
	private String codigoCbf;
	
	@OneToOne
	@JoinColumn(name = "id_modalide")
	private Modalidade modalide;
	
	@ManyToOne
	@JoinColumn(name = "id_modalide_posicao")
	private ModalidadePosicao modalidePosicao;
	
	private Integer perna_preferida;
	
	//DADOS CONTATO
	private Integer prefixo_fone;
	private Integer dddFone;
	private Integer fone;
	private String email;
	
	private String complemento;
	
//	@OneToMany
//	@JoinColumn(name = "id_clube_futebol")
//	private ClubeFutebol clubeFutebol;
//	
//	@OneToMany
//	@JoinColumn(name = "id_empresario")
//	private Empresario empresario;
//	
//	@OneToOne
//	@JoinColumn(name = "id_usuario")
//	private Usuario usuario;
	
	
	public Jogador() {
		
	}

	public Jogador(Integer id, String nome, byte foto, String cpf, LocalDate data_nasc, String nacionalidade,
			String estado_nasc, String municipio_nasc, Double altura, Double peso, Integer profissionalizacao,
			String codigoCbf, Modalidade modalide, ModalidadePosicao modalidePosicao, Integer perna_preferida,
			Integer prefixo_fone, Integer dddFone, Integer fone, String email, String complemento
//			, Usuario usuario
			) {
		super();
		this.id = id;
		this.nome = nome;
		this.foto = foto;
		this.cpf = cpf;
		this.data_nasc = data_nasc;
		this.nacionalidade = nacionalidade;
		this.estado_nasc = estado_nasc;
		this.municipio_nasc = municipio_nasc;
		this.altura = altura;
		this.peso = peso;
		this.profissionalizacao = profissionalizacao;
		this.codigoCbf = codigoCbf;
		this.modalide = modalide;
		this.modalidePosicao = modalidePosicao;
		this.perna_preferida = perna_preferida;
		this.prefixo_fone = prefixo_fone;
		this.dddFone = dddFone;
		this.fone = fone;
		this.email = email;
		this.complemento = complemento;
//		this.usuario = usuario;
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

	public LocalDate getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(LocalDate data_nasc) {
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

	public String getCodigoCbf() {
		return codigoCbf;
	}

	public void setCodigoCbf(String codigoCbf) {
		this.codigoCbf = codigoCbf;
	}

	public Modalidade getModalide() {
		return modalide;
	}

	public void setModalide(Modalidade modalide) {
		this.modalide = modalide;
	}

	public ModalidadePosicao getModalidePosicao() {
		return modalidePosicao;
	}

	public void setModalidePosicao(ModalidadePosicao modalidePosicao) {
		this.modalidePosicao = modalidePosicao;
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

	public Integer getDddFone() {
		return dddFone;
	}

	public void setDddFone(Integer dddFone) {
		this.dddFone = dddFone;
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

//	public ClubeFutebol getClubeFutebol() {
//		return clubeFutebol;
//	}
//
//	public void setClubeFutebol(ClubeFutebol clubeFutebol) {
//		this.clubeFutebol = clubeFutebol;
//	}
//
//	public Empresario getEmpresario() {
//		return empresario;
//	}
//
//	public void setEmpresario(Empresario empresario) {
//		this.empresario = empresario;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

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
		Jogador other = (Jogador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
