package com.joaoldantas.tcc2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoldantas.tcc2.dtos.ExercicioDTO;
import com.joaoldantas.tcc2.services.ExercicioService;

@RestController
@RequestMapping(value = "/exercicios")
public class ExercicioController {
	
	@Autowired
	private ExercicioService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ExercicioDTO>findById(@PathVariable Long id){
		ExercicioDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public Page<ExercicioDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
}
