package com.calculo.tempo.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.calculo.tempo.dtos.EstatisticasDTO;
import com.calculo.tempo.entities.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

	EstatisticasDTO findEstatisticaByData(Date data);
}