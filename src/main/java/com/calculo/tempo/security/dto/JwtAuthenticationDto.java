package com.calculo.tempo.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JwtAuthenticationDto {

	private String login;
	private String senha;

	@NotNull(message = "{login.nulo}")
	@Size(min = 1, message = "{login.vazio}")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotNull(message = "{senha.nulo}")
	@Size(min = 8, max = 16, message = "{senha.invalida}")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
