package com.calculo.tempo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.calculo.tempo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * busca usuario por e-mail
	 * 
	 * @param email
	 * @return
	 */
	@Transactional(readOnly = true)
	Optional<Usuario> findByEmail(String email);

}