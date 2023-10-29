package com.joaoldantas.tcc2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joaoldantas.tcc2.dtos.TreinoDTO;
import com.joaoldantas.tcc2.entities.Exercicio;
import com.joaoldantas.tcc2.entities.Treino;
import com.joaoldantas.tcc2.exceptions.DataBaseException;
import com.joaoldantas.tcc2.exceptions.ResourceNotFoundException;
import com.joaoldantas.tcc2.repositories.ExercicioRepository;
import com.joaoldantas.tcc2.repositories.TreinoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TreinoService {
	
	@Autowired
	private TreinoRepository repository;
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	@Transactional(readOnly = true)
	public TreinoDTO findById(Long id) {
		Treino result = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new TreinoDTO(result);
	}
	@Transactional(readOnly = true)
	public Page<TreinoDTO> findAll(Pageable pageable){
		Page<Treino> result = repository.findAll(pageable);
		return result.map(x -> new TreinoDTO(x));
	}
	
	@Transactional
	public TreinoDTO insert(TreinoDTO dto) {
		Treino entity = new Treino();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TreinoDTO(entity);
	}
	
	@Transactional
	public TreinoDTO update(Long id, TreinoDTO dto) {
		try {
			Treino entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new TreinoDTO(entity);
		}catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional
	public void associar(Long treinoId, Long exercicioId) {
		Treino treino = repository.findById(treinoId).orElse(null);
		Exercicio exercicio = exercicioRepository.findById(exercicioId).orElse(null);
		
		if(treino != null && exercicio != null) {
			exercicio.getTreinos().add(treino);
            treino.getExercicios().add(exercicio);
            repository.save(treino);
            exercicioRepository.save(exercicio);
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encotrado");
		}try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Falha de integridade referencial");
		}
	}
	
	private void copyDtoToEntity(TreinoDTO dto, Treino entity) {
		entity.setNome(dto.getNome());
		entity.setGrupoMuscular(dto.getGrupoMuscular());
	}
	
}
