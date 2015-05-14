package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * The persistent class for the auditoria database table.
 * 
 */
@Entity
@Table(name = "auditoria")
@NamedQuery(name = "Auditoria.findAll", query = "SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "operacion", nullable = false)
	private String operacion;

	@Column(name = "tabla_auditoria")
	private String tablaAuditoria;

	@Column(name = "tabla_id")
	private String tablaId;

	@Column(name = "usuario_id")
	private String usuarioId;

	public Auditoria() {
		super();
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

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
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

	@Override
	public String toString() {
		return "Auditoria [id=" + id + ", descripcion=" + descripcion
				+ ", fechaAuditoria=" + fechaAuditoria + ", operacion="
				+ operacion + ", tablaAuditoria=" + tablaAuditoria
				+ ", tablaId=" + tablaId + ", usuarioId=" + usuarioId + "]";
	}

}