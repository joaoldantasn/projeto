package com.joaoldantas.tcc2.exceptions;

//Excecao para quando não encontra um recurso que esta sendo buscado
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
