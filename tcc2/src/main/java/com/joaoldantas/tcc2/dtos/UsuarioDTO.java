package com.joaoldantas.tcc2.dtos;

import com.joaoldantas.tcc2.entities.Usuario;

public class UsuarioDTO {

	private Long id;
	private String nome;
	
	public UsuarioDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	
}
