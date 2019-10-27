package com.calculo.tempo.services;

import com.calculo.tempo.dtos.VideoDTO;
import com.calculo.tempo.exceptions.TempoException;

public interface VideoService {

	boolean adicionaTempo(VideoDTO video) throws TempoException ;
	boolean populaBanco();
	void apagaTodosRegistros();

}