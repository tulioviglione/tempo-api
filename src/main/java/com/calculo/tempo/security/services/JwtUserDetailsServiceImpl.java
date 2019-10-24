package com.calculo.tempo.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.security.JwtUserFactory;
import com.calculo.tempo.services.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<Usuario> usuario = usuarioService.findByEmail(email);

		if (usuario.isPresent()) {
			return JwtUserFactory.create(usuario.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}
