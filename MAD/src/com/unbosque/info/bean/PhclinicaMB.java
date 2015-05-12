package com.unbosque.info.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.entidad.Phclinica;
import com.unbosque.info.entidad.Tratamiento;
import com.unbosque.info.entidad.Usuario;
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

	List listDietas;

	List listTratamientos;

	List listEnfermedades;

	List listPacientes;

	private Integer id;

	private Integer idPaciente;

	private Timestamp fechaHclinica;

	private Integer idEnfermedad;

	private Integer idTratamiento;

	private Integer idDieta;

	private String estado;

	private Phclinica phclinica;

	public void addHistoriaClinica() {
		RequestContext context = RequestContext.getCurrentInstance();

		java.util.Date now = new java.util.Date();
		fechaHclinica = new java.sql.Timestamp(now.getTime());

		if (idPaciente == 0) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Seleccione Paciente ", "Seleccione Paciente "));
		} else {
			Phclinica phclinica = new Phclinica();

			phclinica.setId(id);
			phclinica.setEstado("A");
			phclinica.setFechaHclinica(fechaHclinica);
			phclinica.setIdDieta(idDieta);
			phclinica.setIdEnfermedad(idEnfermedad);
			phclinica.setIdPaciente(idPaciente);
			phclinica.setIdTratamiento(idTratamiento);

			getPhclinicaService().addPhclinica(phclinica);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Agregado Exitosamente", "Agregado Exitosamente"));
			reset();

		}
	}

	public void reset() {

		this.setEstado("");
		this.setFechaHclinica(null);
		this.setId(0);
		this.setIdDieta(0);
		this.setIdEnfermedad(0);
		this.setIdPaciente(0);
		this.setIdTratamiento(0);

	}

	public List<Phclinica> getPhclinicasList() {
		phclinicaList = new ArrayList<Phclinica>();

		phclinicaList.addAll(getPhclinicaService().getPhclinicas());

		return phclinicaList;
	}

	public List getDietaByNombre() {

		listDietas = new ArrayList();

		List<Dieta> dietasDao = getPhclinicaService().getDietaByNombre();

		for (int i = 0; i < dietasDao.size(); i++) {
			listDietas.add(dietasDao.get(i).getNombre());
		}

		return listDietas;
	}

	public List getTratamientoByNombre() {

		listTratamientos = new ArrayList();

		List<Tratamiento> tratamientosDao = getPhclinicaService()
				.getTratamientoByNombre();

		for (int i = 0; i < tratamientosDao.size(); i++) {
			listTratamientos.add(tratamientosDao.get(i).getNombre());
		}

		return listTratamientos;
	}

	public List getEnfermedadByNombre() {

		listEnfermedades = new ArrayList();

		List<Enfermedad> enfermedadesDao = getPhclinicaService()
				.getEnfermedadByNombre();

		for (int i = 0; i < enfermedadesDao.size(); i++) {
			listEnfermedades.add(enfermedadesDao.get(i).getNombre());
		}

		return listEnfermedades;
	}

	public List getPacienteByNombre() {

		listPacientes = new ArrayList();

		List<Paciente> pacienteDao = getPhclinicaService()
				.getPacienteByNombre();

		for (int i = 0; i < pacienteDao.size(); i++) {
			listPacientes.add(pacienteDao.get(i).getNombresApellidos());
		}

		return listPacientes;
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

	public List getListDietas() {
		return listDietas;
	}

	public void setListDietas(List listDietas) {
		this.listDietas = listDietas;
	}

	public List getListTratamientos() {
		return listTratamientos;
	}

	public void setListTratamientos(List listTratamientos) {
		this.listTratamientos = listTratamientos;
	}

	public List getListEnfermedades() {
		return listEnfermedades;
	}

	public void setListEnfermedades(List listEnfermedades) {
		this.listEnfermedades = listEnfermedades;
	}

	public List getListPacientes() {
		return listPacientes;
	}

	public void setListPacientes(List listPacientes) {
		this.listPacientes = listPacientes;
	}

}