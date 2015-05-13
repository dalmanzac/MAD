package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the parametro database table.
 * 
 */
@Entity
@Table(name = "parametro")
@NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "modulo", nullable = false)
	private String modulo;

	@Column(name = "parametro", nullable = false)
	private String parametro;

	@Column(name = "valor", nullable = false)
	private String valor;

	public Parametro() {
		super();
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModulo() {
		return this.modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Parametro [id=" + id + ", estado=" + estado + ", modulo="
				+ modulo + ", parametro=" + parametro + ", valor=" + valor
				+ "]";
	}

}