package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name = "usuario")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = -1408956551590180165L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "apellidos_nombres", nullable = false)
	private String apellidosNombres;

	@Column(name = "correo", nullable = false)
	private String correo;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "fecha_clave")
	private Timestamp fechaClave;

	@Column(name = "fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name = "login", nullable = false)
	private String login;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "tipo_usuario")
	private String tipoUsuario;

	public Usuario() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellidosNombres() {
		return this.apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
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

	public Timestamp getFechaClave() {
		return this.fechaClave;
	}

	public void setFechaClave(Timestamp fechaClave) {
		this.fechaClave = fechaClave;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public String toString (){
		return "ID: "+id+"\nUsuer: \n"+login+"\nNombre: "+apellidosNombres+"\nFecha de Creación"+fechaCreacion+"\nFecha de Clave: "+fechaClave
				+"\nEstado: "+estado+"\nCorreo: "+correo+"\nTipo Usuario: "+tipoUsuario;
	}

}