package com.example.traductor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.traductor.model.Palabra;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, String> {

    // Método para buscar una palabra por su nombre original
    public Palabra findByOriginal(String original);

}
