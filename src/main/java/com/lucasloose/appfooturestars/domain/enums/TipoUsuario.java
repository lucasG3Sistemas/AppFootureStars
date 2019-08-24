package com.lucasloose.appfooturestars.domain.enums;

public enum TipoUsuario {
	
	JOGADORFUTEBOL(1, "Jogador de Futebol"),
	CLUBEFUTEBOL(2, "Clube de Futebol"),
	EMPRESARIO(3, "Empresário/Olheiro");
	
	private Integer id;
	private String descricao;
	
	private TipoUsuario(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoUsuario toEnum(Integer id) {
		
		if (id == null) {
			return null;
		}
		
		for (TipoUsuario x : TipoUsuario.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("ID inválido: " + id);
	}
	
}
