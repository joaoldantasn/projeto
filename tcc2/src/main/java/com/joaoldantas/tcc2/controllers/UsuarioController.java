package com.joaoldantas.tcc2.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.joaoldantas.tcc2.dtos.UsuarioDTO;
import com.joaoldantas.tcc2.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALUNO')")
	@GetMapping(value = "/me")
	public ResponseEntity<UsuarioDTO> getMe() {
		UsuarioDTO dto = service.getMe();
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO>findById(@PathVariable Long id){
		UsuarioDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public Page<UsuarioDTO> findAll(Pageable pageable){
		return service.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert(@Valid @RequestBody UsuarioDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	 @PostMapping("/adicionar")
	    public ResponseEntity<String> associarTreinoExercicio(
	            @RequestParam("usuarioId") Long usuarioId,
	            @RequestParam("treinoId") Long treinoId) {
	        service.associar(usuarioId, treinoId);
	        return ResponseEntity.ok("Associação realizada com sucesso.");
	    }
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable Long id, @RequestBody UsuarioDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
