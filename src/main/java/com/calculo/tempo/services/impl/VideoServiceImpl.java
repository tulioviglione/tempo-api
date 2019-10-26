package com.calculo.tempo.services.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calculo.tempo.dtos.VideoDTO;
import com.calculo.tempo.entities.Video;
import com.calculo.tempo.exceptions.TempoException;
import com.calculo.tempo.repositories.VideoRepository;
import com.calculo.tempo.services.VideoService;

@Service
public class VideoServiceImpl implements VideoService {

	private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

	@Autowired
	private VideoRepository videoRepository;
	
	@Override
	public boolean adicionaTempo(VideoDTO dto) throws TempoException {
		Video video = new Video();
		video.setDuracao(dto.getDuracao());
		video.setData(new Timestamp(dto.getTimestamp()));
		LocalDateTime dataAtual = LocalDateTime.now();
		if (ChronoUnit.SECONDS.between(((Timestamp)video.getData()).toLocalDateTime(), dataAtual) <= 60) {
			this.salvaRegistro(video);
			return true;
		} else {
			throw new TempoException("Timestamp enviado fora do período: " + video.getData());
		}
	}

	private synchronized void salvaRegistro(Video video) {
		log.debug("Salvando registro Video");
		videoRepository.save(video);
	}

	@Override
	public void populaBanco() {
		LocalDateTime dataInicial = LocalDateTime.now();
		while(ChronoUnit.SECONDS.between(dataInicial, LocalDateTime.now()) <= 60) {
			VideoDTO dto = new VideoDTO();
			dto.setDuracao(getDouble());
			dto.setTimestamp(System.currentTimeMillis());
			try {
				adicionaTempo(dto);
			} catch (TempoException e) {
				log.debug("Sem necessidade de tratar exceção, execução somente para carga no banco");
			}
		}
	}

	private Double getDouble() {
		double min = 200;
		double max = 201;
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}

}