package com.calculo.tempo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * Entidade Video
 *
 * @author Tulio Viglione
 */
@Entity
@Table(name = "video")
public class Video extends Generics implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "duracao", precision=10, scale=2, nullable = false)
	private Double duracao;

	@Column(name = "data", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	public Double getDuracao() {
		return duracao;
	}

	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	
}