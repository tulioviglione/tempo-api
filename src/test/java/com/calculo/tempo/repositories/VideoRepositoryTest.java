package com.calculo.tempo.repositories;


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
		this.videoRepository.save(video);
	}


	@AfterEach
	public final void tearDown() {
		this.videoRepository.deleteAll();
	}

	private Double getDouble() {
		Random r = new Random();
		return r.nextDouble();
	}
	
	
}
