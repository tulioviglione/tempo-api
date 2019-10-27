package com.calculo.tempo.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.repositories.UsuarioRepository;
import com.calculo.tempo.security.utils.PasswordUtils;
import com.calculo.tempo.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> findLogin(String login) {
		log.debug("pesquisa usuário por login");
		return usuarioRepository.findByLogin(login);
	}

	@Override
	public Usuario cadastraNovoUsuario(Usuario usuario) {
		log.debug("cadastra novo usuario no sistema");
		usuario.setSenha(PasswordUtils.gerarBCrypt(usuario.getSenha()));
		try {
			return usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException e) {
			log.debug("Usuário já cadastrado no banco");
			return null;
		}
	}

}