package com.joaoldantas.tcc2.dtos;

import com.joaoldantas.tcc2.entities.Grupo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GrupoDTO {
	
	private Long id;
	@Size(min = 3, max = 80, message = "Nome Precisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String nome;
	private String urlImage;
	@Size(min = 3, max = 80, message = "Nome Precisa ter de 3 a 80 caracteres")
	@NotBlank(message = "Campo requerido")
	private String palavraChave;
	
	public GrupoDTO(Long id, String nome, String urlImage, String palavraChave) {
		this.id = id;
		this.nome = nome;
		this.urlImage = urlImage;
		this.palavraChave = palavraChave;
	}
	
	public GrupoDTO(Grupo entity) {
		id = entity.getId();
		nome = entity.getNome();
		urlImage = entity.getUrlImage();
		palavraChave = entity.getPalavraChave();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public String getPalavraChave() {
		return palavraChave;
	}
	
	
}
