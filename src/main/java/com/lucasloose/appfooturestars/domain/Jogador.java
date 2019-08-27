package com.lucasloose.appfooturestars.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nasc;
	
	private String nacionalidade;
	private String estado_nasc;
	private String municipio_nasc;
	private Integer sexo;
	private Double altura;
	private Double peso;
	
	//DADOS JOGADOR
	private Integer profissionalizacao;
	private String codigo_cbf;
	
//	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "JOGADOR_MODALIDADE",
		joinColumns = @JoinColumn(name = "id_modalidade"),
		inverseJoinColumns = @JoinColumn(name = "id_jogador")
	)
	private List<Modalidade> modalidades = new ArrayList<Modalidade>();
	
//	@JsonManagedReference
	@ManyToMany
	@JoinTable(name = "JOGADOR_MODALIDADE_POSICAO",
		joinColumns = @JoinColumn(name = "id_modalidade_posicao"),
		inverseJoinColumns = @JoinColumn(name = "id_jogador")
	)
	private List<ModalidadePosicao> posicoes = new ArrayList<ModalidadePosicao>();
	
	private Integer perna_preferida;
	
	//DADOS CONTATO
	private Integer prefixo_fone;
	private Integer ddd_fone;
	private Integer fone;
	private String email;
	
	private String complemento;
	
//	@JsonManagedReference
	@OneToMany(mappedBy="jogador")
	private List<JogadorLance> lances = new ArrayList<JogadorLance>();
	
//	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_clube_futebol")
	private ClubeFutebol clubeFutebol;
	
//	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "id_empresario")
	private Empresario empresario;
	
//	@JsonBackReference
	@JsonIgnore
	@ManyToMany(mappedBy="jogadores")
	private List<ListaObservacao> listasObservacoes = new ArrayList<ListaObservacao>();
	
//	@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy="jogador")
	private List<HistoricoContratacao> historicoContratacoes = new ArrayList<HistoricoContratacao>();
	
//	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	
	public Jogador() {
		
	}

	public Jogador(Integer id, String nome, byte foto, String cpf, Date data_nasc, String nacionalidade,
			String estado_nasc, String municipio_nasc, Integer sexo, Double altura, Double peso, Integer profissionalizacao,
			String codigo_cbf, Integer perna_preferida, Integer prefixo_fone, Integer ddd_fone, Integer fone,
			String email, String complemento, Usuario usuario
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
		this.sexo = sexo;
		this.altura = altura;
		this.peso = peso;
		this.profissionalizacao = profissionalizacao;
		this.codigo_cbf = codigo_cbf;
		this.perna_preferida = perna_preferida;
		this.prefixo_fone = prefixo_fone;
		this.ddd_fone = ddd_fone;
		this.fone = fone;
		this.email = email;
		this.complemento = complemento;
		this.usuario = usuario;
	}

	public Jogador(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

	public List<Modalidade> getModalidades() {
		return modalidades;
	}

	public void setModalidades(List<Modalidade> modalidadesJogadores) {
		this.modalidades = modalidadesJogadores;
	}
	
	

	public List<ModalidadePosicao> getModalidadesPosicoes() {
		return posicoes;
	}

	public void setModalidadesPosicoes(List<ModalidadePosicao> modalidadesPosicoes) {
		this.posicoes = modalidadesPosicoes;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}	
	
	public List<JogadorLance> getLances() {
		return lances;
	}

	public void setLances(List<JogadorLance> lances) {
		this.lances = lances;
	}

	public ClubeFutebol getClubeFutebol() {
		return clubeFutebol;
	}

	public void setClubeFutebol(ClubeFutebol clubeFutebol) {
		this.clubeFutebol = clubeFutebol;
	}

	public Empresario getEmpresario() {
		return empresario;
	}

	public void setEmpresario(Empresario empresario) {
		this.empresario = empresario;
	}

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
		Jogador other = (Jogador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
