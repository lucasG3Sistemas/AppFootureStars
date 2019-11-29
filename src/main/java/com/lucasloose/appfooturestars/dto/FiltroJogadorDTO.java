package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import com.lucasloose.appfooturestars.services.validation.JogadorUpdate;

@JogadorUpdate
public class FiltroJogadorDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idModalidade;
	private Integer idPosicao;
    private Integer sexo;
    private Double alturaInicial;
	private Double alturaFinal;
	private Double pesoInicial;
	private Double pesoFinal;
    private Integer profissionalizacao;
    private Integer perna_preferida;
		
	
	public FiltroJogadorDTO() {
		
	}
	

	public Integer getIdModalidade() {
		return idModalidade;
	}

	public void setIdModalidade(Integer idModalidade) {
		this.idModalidade = idModalidade;
	}

	public Integer getIdPosicao() {
		return idPosicao;
	}

	public void setIdPosicao(Integer idPosicao) {
		this.idPosicao = idPosicao;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Double getAlturaInicial() {
		return alturaInicial;
	}

	public void setAlturaInicial(Double alturaInicial) {
		this.alturaInicial = alturaInicial;
	}

	public Double getAlturaFinal() {
		return alturaFinal;
	}

	public void setAlturaFinal(Double alturaFinal) {
		this.alturaFinal = alturaFinal;
	}

	public Double getPesoInicial() {
		return pesoInicial;
	}

	public void setPesoInicial(Double pesoInicial) {
		this.pesoInicial = pesoInicial;
	}

	public Double getPesoFinal() {
		return pesoFinal;
	}

	public void setPesoFinal(Double pesoFinal) {
		this.pesoFinal = pesoFinal;
	}

	public Integer getProfissionalizacao() {
		return profissionalizacao;
	}

	public void setProfissionalizacao(Integer profissionalizacao) {
		this.profissionalizacao = profissionalizacao;
	}

	public Integer getPerna_preferida() {
		return perna_preferida;
	}

	public void setPerna_preferida(Integer perna_preferida) {
		this.perna_preferida = perna_preferida;
	}

}
