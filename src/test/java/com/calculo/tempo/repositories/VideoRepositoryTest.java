package com.calculo.tempo.repositories;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Timestamp;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.calculo.tempo.entities.Video;

@SpringBootTest
@ActiveProfiles("test")
public class VideoRepositoryTest {

	@Autowired
	private VideoRepository videoRepository;

	@Test
	public void salvaRegistro() throws Exception {
		Video video = new Video();
		video.setDuracao(getDouble());
		video.setData(new Timestamp(System.currentTimeMillis()));
		assertNotNull(this.videoRepository.save(video));
	}


	@AfterEach
	public final void tearDown() {
		this.videoRepository.deleteAll();
	}

	private Double getDouble() {
		double min = 200;
		double max = 201;
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}
	
	
}
