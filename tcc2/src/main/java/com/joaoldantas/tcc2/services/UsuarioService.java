package com.joaoldantas.tcc2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joaoldantas.tcc2.dtos.UsuarioDTO;
import com.joaoldantas.tcc2.entities.Role;
import com.joaoldantas.tcc2.entities.Treino;
import com.joaoldantas.tcc2.entities.Usuario;
import com.joaoldantas.tcc2.exceptions.DataBaseException;
import com.joaoldantas.tcc2.exceptions.ResourceNotFoundException;
import com.joaoldantas.tcc2.projections.UserDetailsProjection;
import com.joaoldantas.tcc2.repositories.TreinoRepository;
import com.joaoldantas.tcc2.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserDetailsProjection> result = repository.searchUsuarioAndRolesByCpf(username);
		
		if(result.size() == 0) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		
		Usuario usuario = new Usuario();
		usuario.setCpf(result.get(0).getUsername());
		usuario.setSenha(result.get(0).getPassword());
		for (UserDetailsProjection projection : result) {
			usuario.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
		}
		
		return usuario;
	}
	
	protected Usuario authenticated() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
			String username = jwtPrincipal.getClaim("username");
			
			return repository.findByEmail(username).get();
		}catch(Exception e) {
			throw new UsernameNotFoundException("Email not found");
		}
		
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO getMe() {
		Usuario usuario = authenticated();
		return new UsuarioDTO(usuario);
	}
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private TreinoRepository treinoRepository;
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Usuario result = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new UsuarioDTO(result);
	}
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(Pageable pageable){
		Page<Usuario> result = repository.findAll(pageable);
		return result.map(x -> new UsuarioDTO(x));
	}
	
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}
	
	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		try {
			Usuario entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UsuarioDTO(entity);
		}catch(EntityNotFoundException e){
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}
	
	@Transactional
	public void associar(Long usuarioId, Long treinoId) {
		Usuario usuario = repository.findById(usuarioId).orElse(null);
		Treino treino = treinoRepository.findById(treinoId).orElse(null);
		
		if(treino != null && usuario != null) {
			usuario.getTreinos().add(treino);
            treino.getUsuarios().add(usuario);
            repository.save(usuario);
            treinoRepository.save(treino);
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
	
	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNome(dto.getNome());
	}

	
}
