package com.joaoldantas.tcc2.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaoldantas.tcc2.dtos.TreinoDTO;
import com.joaoldantas.tcc2.services.TreinoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/treinos")
public class TreinoController {
	
	@Autowired
	private TreinoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TreinoDTO>findById(@PathVariable Long id){
		TreinoDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public Page<TreinoDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<TreinoDTO> insert(@Valid @RequestBody TreinoDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	 @PostMapping("/adicionar")
	    public ResponseEntity<String> associarTreinoExercicio(
	            @RequestParam("treinoId") Long treinoId,
	            @RequestParam("exercicioId") Long exercicioId) {
	        service.associar(treinoId, exercicioId);
	        return ResponseEntity.ok("Associação realizada com sucesso.");
	    }
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TreinoDTO> update(@Valid @PathVariable Long id, @RequestBody TreinoDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
