package com.example.traductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.traductor.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	Categoria findByDescripcion(String descripcion);

	
	
}
