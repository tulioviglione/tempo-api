package com.calculo.tempo.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.enums.PerfilEnum;
import com.calculo.tempo.repositories.UsuarioRepository;
import com.calculo.tempo.security.utils.PasswordUtils;
import com.calculo.tempo.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<Usuario> findByEmail(String email) {
		log.debug("pesquisa usuário por e-mail");
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario cadastraNovoUsuario(Usuario usuario) {
		log.debug("cadastra novo usuario no sistema");
		usuario.setPerfil(PerfilEnum.USUARIO);
		usuario.setSenha(PasswordUtils.gerarBCrypt(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}

}