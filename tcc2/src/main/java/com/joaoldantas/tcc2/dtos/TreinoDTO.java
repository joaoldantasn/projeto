package com.joaoldantas.tcc2.dtos;

import com.joaoldantas.tcc2.entities.Treino;

public class TreinoDTO {
	private Long id;
	private String nome;
	private String grupoMuscular;
	
	public TreinoDTO(Long id,String nome, String grupoMuscular) {
		this.id = id;
		this.nome = nome;
		this.grupoMuscular = grupoMuscular;
	}
	
	public TreinoDTO(Treino entity) {
		id = entity.getId();
		nome = entity.getNome();
		grupoMuscular = entity.getGrupoMuscular();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getGrupoMuscular() {
		return grupoMuscular;
	}
	
}
