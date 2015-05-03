package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the enfermedad database table.
 * 
 */
@Entity
@Table(name = "enfermedad")
@NamedQuery(name = "Enfermedad.findAll", query = "SELECT e FROM Enfermedad e")
public class Enfermedad implements Serializable {
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

	public Enfermedad() {
		super();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Enfermedad [id=" + id + ", descripcion=" + descripcion
				+ ", estado=" + estado + ", nombre=" + nombre + "]";
	}

}