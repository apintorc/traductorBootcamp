package com.example.traductor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.traductor.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Usuario findByUser(String user);

}
