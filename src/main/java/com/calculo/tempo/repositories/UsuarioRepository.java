package com.calculo.tempo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.calculo.tempo.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * busca usuario por login
	 * 
	 * @param login
	 * @return
	 */
	@Transactional(readOnly = true)
	Optional<Usuario> findByLogin(String login);

}