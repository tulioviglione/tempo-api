package com.calculo.tempo.services;

import java.util.Optional;

import com.calculo.tempo.entities.Usuario;

public interface UsuarioService {

	/**
	 * Filtra usuario por login
	 * 
	 * @param login
	 * @return
	 */
	Optional<Usuario> findLogin(String login);

	/**
	 * Salva novo Usuario
	 * 
	 * @param usuario
	 * @return
	 */
	Usuario cadastraNovoUsuario(Usuario usuario);

}