package com.calculo.tempo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

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

	@Column(name = "EMAIL", length = 150, nullable = false, unique = true)
	private String email;

	@Column(name = "SENHA", length = 255, nullable = false)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(name = "PERFIL", nullable = false)
	private PerfilEnum perfil;

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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