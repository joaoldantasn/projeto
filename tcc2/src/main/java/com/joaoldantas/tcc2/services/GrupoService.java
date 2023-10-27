package com.joaoldantas.tcc2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joaoldantas.tcc2.dtos.GrupoDTO;
import com.joaoldantas.tcc2.entities.Grupo;
import com.joaoldantas.tcc2.repositories.GrupoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository repository;
	
	@Transactional(readOnly = true)
	public GrupoDTO findById(Long id) {
		Optional<Grupo> result = repository.findById(id);
		Grupo grupo = result.get();
		GrupoDTO dto = new GrupoDTO(grupo);
		return dto;
	}
	@Transactional(readOnly = true)
	public Page<GrupoDTO> findAll(Pageable pageable){
		Page<Grupo> result = repository.findAll(pageable);
		return result.map(x -> new GrupoDTO(x));
	}
	
	@Transactional
	public GrupoDTO insert(GrupoDTO dto) {
		Grupo entity = new Grupo();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new GrupoDTO(entity);
	}
	
	@Transactional
	public GrupoDTO update(Long id, GrupoDTO dto) {
		try {
			Grupo entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new GrupoDTO(entity);
		}catch(EntityNotFoundException e){
			throw new EntityNotFoundException();
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Erro");
		}
	}
	
	private void copyDtoToEntity(GrupoDTO dto, Grupo entity) {
		entity.setNome(dto.getNome());
		entity.setPalavraChave(dto.getPalavraChave());
		entity.setUrlImage(dto.getUrlImage());;
	}
}
