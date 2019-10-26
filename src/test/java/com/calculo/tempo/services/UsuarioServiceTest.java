package com.calculo.tempo.services;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.ParseException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.repositories.UsuarioRepository;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@MockBean
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@BeforeEach
	public void setUp() throws Exception {
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findByLogin(Mockito.anyString())).willReturn(Optional.of(new Usuario()));
	}

	@Test
	public void TestCadastroNovoUsuario() throws ParseException {
		Usuario usuario = new Usuario();
		usuario.setLogin("Login");
		usuario.setSenha("SENHA123");
		usuario = this.usuarioService.cadastraNovoUsuario(usuario);
		assertNotNull(usuario);
	}
	
	@Test
	public void TestFindByLogin() throws ParseException {
		Usuario usuario = this.usuarioService.findLogin(Mockito.anyString()).get();
		assertNotNull(usuario);
	}

}