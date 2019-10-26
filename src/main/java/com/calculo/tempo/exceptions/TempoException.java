package com.calculo.tempo.exceptions;

public class TempoException extends Exception {

	private static final long serialVersionUID = 1L;

	public TempoException(String mensagem) {
		super(mensagem);
	}

	public TempoException(String mensagem, Throwable e) {
		super(mensagem, e);
	}
}
