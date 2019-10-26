package com.calculo.tempo.services;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.calculo.tempo.dtos.VideoDTO;
import com.calculo.tempo.entities.Video;
import com.calculo.tempo.exceptions.TempoException;
import com.calculo.tempo.repositories.VideoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class VideoServiceTest {

	@MockBean
	private VideoRepository videoRepository;

	@Autowired
	private VideoService videoService;

	@BeforeEach
	public void setUp() throws Exception {
		BDDMockito.given(this.videoRepository.save(Mockito.any(Video.class))).willReturn(new Video());
	}

	@Test
	public void TestCadastroNovoRegistro() throws ParseException, TempoException {
		VideoDTO dto = new VideoDTO();
		dto.setDuracao(getDouble());
		dto.setTimestamp(System.currentTimeMillis());
		assertTrue(videoService.adicionaTempo(dto));

	}

	private Double getDouble() {
		Random r = new Random();
		return r.nextDouble();
	}
	
}