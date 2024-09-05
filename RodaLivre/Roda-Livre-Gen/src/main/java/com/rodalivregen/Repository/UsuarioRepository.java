package com.rodalivregen.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodalivregen.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findById(Long id);

}
