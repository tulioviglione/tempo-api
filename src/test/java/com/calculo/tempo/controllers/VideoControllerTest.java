package com.calculo.tempo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

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
	
	@Test
	public void testCarga() throws TempoException {
		ResponseEntity<Object> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/api/tempo/realizaCarga", null, Object.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
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
