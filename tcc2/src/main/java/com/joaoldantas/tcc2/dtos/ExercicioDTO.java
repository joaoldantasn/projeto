package com.joaoldantas.tcc2.dtos;

import com.joaoldantas.tcc2.entities.Exercicio;

public class ExercicioDTO {
	private Long id;
	private String nome;
	private String imgUrl;
	
	public ExercicioDTO(Long id,String nome, String imgUrl) {
		this.id = id;
		this.nome = nome;
		this.imgUrl = imgUrl;
	}
	
	public ExercicioDTO(Exercicio entity) {
		id = entity.getId();
		nome = entity.getNome();
		imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getImgUrl() {
		return imgUrl;
	}

}
