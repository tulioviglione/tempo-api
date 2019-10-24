package com.calculo.tempo.services;

import java.util.Optional;

import com.calculo.tempo.entities.Usuario;

public interface UsuarioService {

	/**
	 * Filtra usuario por e-mail
	 * 
	 * @param email
	 * @return
	 */
	Optional<Usuario> findByEmail(String email);

	/**
	 * Salva novo Usuario
	 * 
	 * @param usuario
	 * @return
	 */
	Usuario cadastraNovoUsuario(Usuario usuario);

}