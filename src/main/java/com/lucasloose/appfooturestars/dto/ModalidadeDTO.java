package com.lucasloose.appfooturestars.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.lucasloose.appfooturestars.domain.Modalidade;

public class ModalidadeDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Campo Obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
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
