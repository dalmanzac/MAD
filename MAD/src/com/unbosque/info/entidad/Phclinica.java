package com.unbosque.info.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the phclinica database table.
 * 
 */
@Entity
@NamedQuery(name="Phclinica.findAll", query="SELECT p FROM Phclinica p")
public class Phclinica implements Serializable {
	private static final long serialVersionUID = 1L;

	private String estado;

	@Column(name="fecha_hclinica")
	private Timestamp fechaHclinica;

	private Integer id;

	@Column(name="id_dieta")
	private Integer idDieta;

	@Column(name="id_enfermedad")
	private Integer idEnfermedad;

	@Column(name="id_paciente")
	private Integer idPaciente;

	@Column(name="id_tratamiento")
	private Integer idTratamiento;

	public Phclinica() {
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaHclinica() {
		return this.fechaHclinica;
	}

	public void setFechaHclinica(Timestamp fechaHclinica) {
		this.fechaHclinica = fechaHclinica;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdDieta() {
		return this.idDieta;
	}

	public void setIdDieta(Integer idDieta) {
		this.idDieta = idDieta;
	}

	public Integer getIdEnfermedad() {
		return this.idEnfermedad;
	}

	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public Integer getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Integer getIdTratamiento() {
		return this.idTratamiento;
	}

	public void setIdTratamiento(Integer idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

}