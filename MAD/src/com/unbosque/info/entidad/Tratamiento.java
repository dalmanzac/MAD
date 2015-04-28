package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the tratamiento database table.
 * 
 */
@Entity
@Table(name = "tratamiento")
@NamedQuery(name = "Tratamiento.findAll", query = "SELECT t FROM Tratamiento t")
public class Tratamiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	public Tratamiento() {
		super();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

}