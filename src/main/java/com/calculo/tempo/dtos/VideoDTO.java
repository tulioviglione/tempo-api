package com.calculo.tempo.dtos;

import javax.validation.constraints.NotNull;

public class VideoDTO {

	private Double duracao;
	private Long timestamp;

	@NotNull(message = "{duracao.nulo}")
	public Double getDuracao() {
		return duracao;
	}

	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}

	@NotNull(message = "{timestamp.nulo}")
	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
