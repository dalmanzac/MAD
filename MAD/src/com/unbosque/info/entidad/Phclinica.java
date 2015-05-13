package com.unbosque.info.entidad;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;

/**
 * The persistent class for the phclinica database table.
 * 
 */
@Entity
@Table(name = "phclinica")
@NamedQuery(name = "Phclinica.findAll", query = "SELECT p FROM Phclinica p")
public class Phclinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "estado", nullable = false)
	private String estado;

	@Column(name = "fecha_hclinica")
	private Timestamp fechaHclinica;

	@Column(name = "id_dieta")
	private String idDieta;

	@Column(name = "id_enfermedad")
	private String idEnfermedad;

	@Column(name = "id_paciente")
	private String idPaciente;

	@Column(name = "id_tratamiento")
	private String idTratamiento;

	public Phclinica() {
		super();
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

	public String getIdDieta() {
		return this.idDieta;
	}

	public void setIdDieta(String idDieta) {
		this.idDieta = idDieta;
	}

	public String getIdEnfermedad() {
		return this.idEnfermedad;
	}

	public void setIdEnfermedad(String idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getIdTratamiento() {
		return this.idTratamiento;
	}

	public void setIdTratamiento(String idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	@Override
	public String toString() {
		return "Phclinica [estado=" + estado + ", fechaHclinica="
				+ fechaHclinica + ", id=" + id + ", idDieta=" + idDieta
				+ ", idEnfermedad=" + idEnfermedad + ", idPaciente="
				+ idPaciente + ", idTratamiento=" + idTratamiento + "]";
	}

}