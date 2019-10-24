package com.calculo.tempo.repositories;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.enums.PerfilEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeEach
	public void setUp() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@mail.com");
		usuario.setPerfil(PerfilEnum.ADMIN);
		usuario.setSenha("SENHA123");
		this.usuarioRepository.save(usuario);
	}

	@AfterEach
	public final void tearDown() {
		this.usuarioRepository.deleteAll();
	}

	@Test
	public void testFindByEmail() {
		assertTrue(usuarioRepository.findByEmail("teste@mail.com").isPresent());
	}

}
