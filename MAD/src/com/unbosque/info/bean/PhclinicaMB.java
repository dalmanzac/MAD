package com.unbosque.info.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.entidad.Phclinica;
import com.unbosque.info.service.PacienteService;
import com.unbosque.info.service.PhclinicaService;

@ManagedBean(name = "phclinicaMB")
@SessionScoped
public class PhclinicaMB implements Serializable {

	private static final long serialVersionUID = -1L;

	private PhclinicaMB registroSeleccionado;

	@ManagedProperty(value = "#{PhclinicaService}")
	PhclinicaService phclinicaService;

	List<Phclinica> phclinicaList;

	private Integer id;

	private Integer idPaciente;

	private Timestamp fechaHclinica;

	private Integer idEnfermedad;

	private Integer idTratamiento;

	private Integer idDieta;

	private String estado;

	private Phclinica phclinica;
	
	public List<Phclinica> getPhclinicasList() {
		phclinicaList = new ArrayList<Phclinica>();

		phclinicaList.addAll(getPhclinicaService().getPhclinicas());

		return phclinicaList;
	}

	public PhclinicaService getPhclinicaService() {
		return phclinicaService;
	}

	public void setPhclinicaService(PhclinicaService phclinicaService) {
		this.phclinicaService = phclinicaService;
	}

	public List<Phclinica> getPhclinicaList() {
		return phclinicaList;
	}

	public void setPhclinicaList(List<Phclinica> phclinicaList) {
		this.phclinicaList = phclinicaList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Timestamp getFechaHclinica() {
		return fechaHclinica;
	}

	public void setFechaHclinica(Timestamp fechaHclinica) {
		this.fechaHclinica = fechaHclinica;
	}

	public Integer getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public Integer getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(Integer idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public Integer getIdDieta() {
		return idDieta;
	}

	public void setIdDieta(Integer idDieta) {
		this.idDieta = idDieta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Phclinica getPhclinica() {
		return phclinica;
	}

	public void setPhclinica(Phclinica phclinica) {
		System.out.println(phclinica.toString());
		this.phclinica = phclinica;
	}
	
	}