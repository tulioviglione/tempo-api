package com.calculo.tempo.enums;

public enum PerfilEnum {

	ADMIN("Administrador"), USUARIO("Usuario");

	private final String descricao;

	private PerfilEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}