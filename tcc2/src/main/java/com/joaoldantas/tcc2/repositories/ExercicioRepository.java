package com.joaoldantas.tcc2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joaoldantas.tcc2.entities.Exercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long>{

}
