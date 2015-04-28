package com.unbosque.info.entidad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the controlador database table.
 * 
 */
@Entity
@NamedQuery(name="Controlador.findAll", query="SELECT c FROM Controlador c")
public class Controlador implements Serializable {
	private static final long serialVersionUID = 1L;

	private String estado;

	private Integer id;

	private String ubicacion;

	public Controlador() {
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

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}