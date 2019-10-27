package com.calculo.tempo.services;

import com.calculo.tempo.dtos.EstatisticasDTO;
import com.calculo.tempo.dtos.VideoDTO;
import com.calculo.tempo.exceptions.TempoException;

public interface VideoService {

	/**
	 * Adiciona novo registro
	 * 
	 * @param video
	 * @return
	 * @throws TempoException
	 */
	boolean adicionaTempo(VideoDTO video) throws TempoException ;

	/**
	 * metodo popula banco para teste da aplicação
	 * 
	 * @return
	 */
	boolean populaBanco();
	
	/**
	 * apaga todos os registros de tempo no banco
	 */
	void apagaTodosRegistros();
	
	/**
	 * retorna a estaticas referente aos ultimos 60 segundos
	 * 
	 * @return
	 */
	EstatisticasDTO buscaEstatistica();

}