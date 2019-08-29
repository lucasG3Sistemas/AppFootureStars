package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import com.lucasloose.appfooturestars.domain.Modalidade;

public class ModalidadeDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;

	
	public ModalidadeDTO() {
		
	}

	public ModalidadeDTO(Modalidade modalidade) {
		this.id = modalidade.getId();
		this.descricao = modalidade.getDescricao();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
