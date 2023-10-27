package com.joaoldantas.tcc2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joaoldantas.tcc2.dtos.ExercicioDTO;
import com.joaoldantas.tcc2.entities.Exercicio;
import com.joaoldantas.tcc2.exceptions.ResourceNotFoundException;
import com.joaoldantas.tcc2.repositories.ExercicioRepository;

@Service
public class ExercicioService {
	
	@Autowired
	private ExercicioRepository repository;
	
	@Transactional(readOnly = true)
	public ExercicioDTO findById(Long id) {
		Exercicio result = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ExercicioDTO(result);
	}
	@Transactional(readOnly = true)
	public Page<ExercicioDTO> findAll(Pageable pageable){
		Page<Exercicio> result = repository.findAll(pageable);
		return result.map(x -> new ExercicioDTO(x));
	}
	
}
