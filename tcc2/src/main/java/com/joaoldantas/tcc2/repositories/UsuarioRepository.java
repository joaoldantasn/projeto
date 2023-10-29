package com.joaoldantas.tcc2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joaoldantas.tcc2.entities.Usuario;
import com.joaoldantas.tcc2.projections.UserDetailsProjection;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(nativeQuery = true, value = """
			SELECT tb_usuario.cpf AS username, tb_usuario.senha AS password, tb_role.id AS roleId, tb_role.authority
			FROM tb_usuario
			INNER JOIN tb_usuario_role ON tb_usuario.id = tb_usuario_role.usuario_id
			INNER JOIN tb_role ON tb_role.id = tb_usuario_role.role_id
			WHERE tb_usuario.cpf = :cpf
		""")
	List<UserDetailsProjection> searchUsuarioAndRolesByCpf(String cpf);
	
	
	Optional<Usuario> findByEmail(String email);
	
}
