package com.unbosque.info.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the auditoria database table.
 * 
 */
@Entity
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String descripcion;

	@Column(name="fecha_auditoria")
	private Time fechaAuditoria;

	private String operacion;

	@Column(name="tabla_auditoria")
	private String tablaAuditoria;

	@Column(name="tabla_id")
	private String tablaId;

	@Column(name="usuario_id")
	private String usuarioId;

	public Auditoria() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Time getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Time fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getOperacion() {
		return this.operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getTablaAuditoria() {
		return this.tablaAuditoria;
	}

	public void setTablaAuditoria(String tablaAuditoria) {
		this.tablaAuditoria = tablaAuditoria;
	}

	public String getTablaId() {
		return this.tablaId;
	}

	public void setTablaId(String tablaId) {
		this.tablaId = tablaId;
	}

	public String getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

}