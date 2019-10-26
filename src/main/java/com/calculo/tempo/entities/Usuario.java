package com.calculo.tempo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.calculo.tempo.dtos.UsuarioDTO;
import com.calculo.tempo.enums.PerfilEnum;

/**
 * 
 * Entidade Usuario
 *
 * @author Tulio Viglione
 */
@Entity
@Table(name = "usuario")
public class Usuario extends Generics implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "LOGIN", length = 150, nullable = false, unique = true)
	private String login;

	@Column(name = "SENHA", length = 255, nullable = false)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL", nullable = false)
	private PerfilEnum perfil;

	public Usuario() {
		
	}
	
	public Usuario(UsuarioDTO dto) {
		super();
		setId(dto.getId());
		this.login = dto.getLogin();
		this.senha = dto.getSenha();
		this.perfil = dto.getPerfil();
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

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