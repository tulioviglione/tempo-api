package com.calculo.tempo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.calculo.tempo.dtos.UsuarioDTO;
import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.services.UsuarioService;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UsuarioControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private UsuarioService usuarioService;
	
	@Test
	public void testAddUsuario() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setLogin("login");
		dto.setSenha("senha123");
		
		BDDMockito.given(this.usuarioService.cadastraNovoUsuario(Mockito.any(Usuario.class))).willReturn(new Usuario());
		ResponseEntity<Object> responseEntity = this.restTemplate
			.postForEntity("http://localhost:" + port + "/api/usuarios", dto, Object.class);
		assertEquals(200, responseEntity.getStatusCodeValue());

		BDDMockito.given(this.usuarioService.cadastraNovoUsuario(Mockito.any(Usuario.class))).willReturn(null);
		responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/api/usuarios", dto, Object.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
		
		dto.setLogin(null);
		responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/api/usuarios", dto, Object.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
		
		dto.setSenha(null);
		dto.setLogin("login");
		responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/api/usuarios", dto, Object.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}
	
}	
