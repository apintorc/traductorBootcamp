package com.example.traductor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.traductor.model.Palabra;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, String> {
    public Palabra findByOriginal(String original);

}
