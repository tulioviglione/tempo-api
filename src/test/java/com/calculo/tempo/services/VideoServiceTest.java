package com.calculo.tempo.services;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.util.Random;

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
	
	@Test
	public void TestCargaDados() {
		videoService.populaBanco();
	}

	private Double getDouble() {
		double min = 200;
		double max = 201;
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}
	
}