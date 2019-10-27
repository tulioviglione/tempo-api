package com.calculo.tempo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Random;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.calculo.tempo.dtos.VideoDTO;
import com.calculo.tempo.exceptions.TempoException;
import com.calculo.tempo.services.VideoService;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class VideoControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@MockBean
	private VideoService videoService;
	
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	 
    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
    }
	
	@Test
	public void testCarga() throws TempoException {
		ResponseEntity<Object> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/api/tempo/realizaCarga", null, Object.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void realizaPesquisa() {
		ResponseEntity<Object> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/api/tempo/", null, Object.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	@WithMockUser
	public void apagaRegistros() throws Exception {
		mvc.perform(delete("http://localhost:" + port + "/api/tempo/")).andExpect(status().isNoContent());
	}
	
	@Test
	public void apagaRegistrosSemUsuario() throws Exception {
		mvc.perform(delete("http://localhost:" + port + "/api/tempo/")).andExpect(status().isUnauthorized());
	}

	@Test
	public void testAddUsuario() throws TempoException {
		VideoDTO dto = new VideoDTO();
		dto.setDuracao(getDouble());
		dto.setTimestamp(System.currentTimeMillis());
		
		BDDMockito.given(this.videoService.adicionaTempo(Mockito.any(VideoDTO.class))).willReturn(true);
		ResponseEntity<Object> responseEntity = this.restTemplate
			.postForEntity("http://localhost:" + port + "/api/tempo/adicionaRegistro", dto, Object.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
		
		BDDMockito.given(this.videoService.adicionaTempo(Mockito.any(VideoDTO.class))).willThrow(TempoException.class);
		responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/api/tempo/adicionaRegistro", dto, Object.class);
		assertEquals(204, responseEntity.getStatusCodeValue());
		
		dto.setDuracao(null);
		responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/api/tempo/adicionaRegistro", dto, Object.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
		
		dto.setDuracao(getDouble());
		dto.setTimestamp(null);
		responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/api/tempo/adicionaRegistro", dto, Object.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	
	
	private Double getDouble() {
		double min = 200;
		double max = 201;
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}
	
}	
