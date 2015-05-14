package com.unbosque.info.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.service.AuditoriaService;

@ManagedBean(name = "auditoriaMB")
@SessionScoped
public class AuditoriaMB {

	private static final long serialVersionUID = -1L;

	private AuditoriaMB registroSeleccionado;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{AuditoriaService}")
	AuditoriaService auditoriaService;

	List<Auditoria> auditoriaList;

	private int id;
	private String descripcion;
	private Timestamp fechaAuditoria;
	private String operacion;
	private String tablaAuditoria;
	private String tablaId;
	private String usuarioId;
	private Auditoria reportes = new Auditoria();

	public List<Auditoria> getAuditoriasList() {
		auditoriaList = new ArrayList<Auditoria>();

		auditoriaList.addAll(getAuditoriaService().getAuditorias());

		return auditoriaList;
	}

	public AuditoriaMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(AuditoriaMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public AuditoriaService getAuditoriaService() {
		return auditoriaService;
	}

	public void setAuditoriaService(AuditoriaService auditoriaService) {
		this.auditoriaService = auditoriaService;
	}

	public List<Auditoria> getAuditoriaList() {
		return auditoriaList;
	}

	public void setAuditoriaList(List<Auditoria> auditoriaList) {
		this.auditoriaList = auditoriaList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getTablaAuditoria() {
		return tablaAuditoria;
	}

	public void setTablaAuditoria(String tablaAuditoria) {
		this.tablaAuditoria = tablaAuditoria;
	}

	public String getTablaId() {
		return tablaId;
	}

	public void setTablaId(String tablaId) {
		this.tablaId = tablaId;
	}

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Auditoria getReportes() {
		return reportes;
	}

	public void setReportes(Auditoria reportes) {
		this.reportes = reportes;
	}

}
