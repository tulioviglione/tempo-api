package com.calculo.tempo.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.calculo.tempo.entities.Usuario;
import com.calculo.tempo.enums.PerfilEnum;

public class UsuarioDTO {

	private Long id;
	private String login;
	private String senha;
	private PerfilEnum perfil;

	public UsuarioDTO() {
		// construtor padrão
	}
	
	public UsuarioDTO(Usuario usuario) {
		super();
		this.id = usuario.getId();
		this.login = usuario.getLogin();
		this.perfil = usuario.getPerfil();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

}
