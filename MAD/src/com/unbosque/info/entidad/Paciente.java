package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name = "paciente")
@NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "correo", nullable = false)
	private String correo;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "sexo", nullable = false)
	private String sexo;

	@Column(name = "direccion", nullable = false)
	private String direccion;

	@Column(name = "identificacion", nullable = false)
	private Integer identificacion;

	@Column(name = "nombres_apellidos")
	private String nombresApellidos;

	@Column(name = "prog_nutricion")
	private String progNutricion;

	@Column(name = "telefono", nullable = false)
	private String telefono;

	public Paciente() {
		super();
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public Integer getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(Integer identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombresApellidos() {
		return this.nombresApellidos;
	}

	public void setNombresApellidos(String nombresApellidos) {
		this.nombresApellidos = nombresApellidos;
	}

	public String getProgNutricion() {
		return this.progNutricion;
	}

	public void setProgNutricion(String progNutricion) {
		this.progNutricion = progNutricion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}