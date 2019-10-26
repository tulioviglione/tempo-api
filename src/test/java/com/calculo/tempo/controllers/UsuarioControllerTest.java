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
	
	@BeforeEach
	private void executeService() {
		BDDMockito.given(this.usuarioService.cadastraNovoUsuario(Mockito.any(Usuario.class))).willReturn(new Usuario());
	}
	
	@Test
	public void testAddUsuario() {
		UsuarioDTO dto = new UsuarioDTO();
		dto.setLogin("login");
		dto.setSenha("senha123");
		
		ResponseEntity<Object> responseEntity = this.restTemplate
			.postForEntity("http://localhost:" + port + "/api/usuarios", dto, Object.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
		
		dto.setLogin(null);
		responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/api/usuarios", dto, Object.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
		
		dto.setSenha(null);
		dto.setLogin("login");
		responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/api/usuarios", dto, Object.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}
	
}	
	
//	@Autowired
//	private MockMvc mvc;

//	@MockBean
//	private UsuarioService usuarioService;
	
//	@Test
//	public void cadastraNovoUsuario() throws Exception {
//		mvc.perform()
//		BDDMockito.given(this.usuarioService.cadastraNovoUsuario(Mockito.any(Usuario.class))).willReturn(new Usuario());
//
//		mvc.perform(MockMvcRequestBuilders.post("/api/usuarios")
//				.content("{\"login\": \"login\",\"senha\": \"12345678\"}")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
//		
//		mvc.perform(MockMvcRequestBuilders.post("/api/usuarios")
//				.content("{\"login\": \"\",\"senha\": \"\"}")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors").isNotEmpty());
//	}
