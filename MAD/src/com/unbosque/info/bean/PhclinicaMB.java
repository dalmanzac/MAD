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
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.entidad.Phclinica;
import com.unbosque.info.entidad.Tratamiento;
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.PacienteService;
import com.unbosque.info.service.PhclinicaService;
import com.unbosque.info.util.CifrarClave;
import com.unbosque.info.util.Validacion;

@ManagedBean(name = "phclinicaMB")
@SessionScoped
public class PhclinicaMB implements Serializable {

	private static final long serialVersionUID = -1L;

	private PhclinicaMB registroSeleccionado;

	@ManagedProperty(value = "#{PhclinicaService}")
	PhclinicaService phclinicaService;

	PacienteService pacienteService;

	List<Phclinica> phclinicaList;

	List listDietas;

	List listTratamientos;

	List listEnfermedades;

	List listPacientes;

	private Integer id;

	private String idPaciente;

	private Timestamp fechaHclinica;

	private String idEnfermedad;

	private String idTratamiento;

	private String idDieta;

	private String estado;

	private Phclinica phclinica;

	public void addPhclinica() {
		RequestContext context = RequestContext.getCurrentInstance();
		Paciente temp = getPhclinicaService().getPacienteByUser(idPaciente);

		java.util.Date now = new java.util.Date();
		fechaHclinica = new java.sql.Timestamp(now.getTime());

		if (idTratamiento.equals("0")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Seleccione Tratamiento ",
							"Seleccione Tratamiento "));
		} else if (idPaciente.equals("")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Seleccione Paciente ", "Seleccione Paciente "));
		} else if (idEnfermedad.equals("0")) {
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Seleccione Enfermedad ",
									"Seleccione Enfermedad "));
		} else if (idDieta.equals("0")) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Seleccione Dieta ", "Seleccione Dieta "));

		} else if (temp.getEstado().equals("I")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Paciente Inactivo ", "Paciente Inactivo "));
		} else if (temp.getProgNutricion().equals("No   ")) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Paciente No Inscrito al Servicio de Nutrición. ",
							"Paciente No Inscrito al Servicio de Nutrición. "));

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

		}

	}

	public void reset() {

		this.setEstado("");
		this.setFechaHclinica(null);
		this.setId(0);
		this.setIdDieta("");
		this.setIdEnfermedad("");
		this.setIdPaciente("");
		this.setIdTratamiento("");

	}

	public void modPhclinica() {

		RequestContext context = RequestContext.getCurrentInstance();
		System.out.println(phclinica.toString());

		if (idEnfermedad.equals("0")) {
			phclinica.setIdEnfermedad(phclinica.getIdEnfermedad());
		} else {
			phclinica.setIdEnfermedad(idEnfermedad);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (idTratamiento.equals("0")) {
			phclinica.setIdTratamiento(phclinica.getIdTratamiento());
		} else {
			phclinica.setIdTratamiento(idTratamiento);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (idDieta.equals("0")) {
			phclinica.setIdDieta(phclinica.getIdDieta());
		} else {
			phclinica.setIdDieta(idDieta);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (estado.equals("")) {
			phclinica.setEstado(phclinica.getEstado());
		} else {
			phclinica.setEstado(estado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));

		}
		getPhclinicaService().updatePhclinica(phclinica);
		reset();
	}

	public String deletePhclinica(Phclinica phclinica) {
		System.out.println(phclinica.getIdPaciente());
		try {

			phclinica.setEstado("I");
			getPhclinicaService().updatePhclinica(phclinica);
			FacesContext.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Eliminado Exitosamente",
									"Eliminado Exitosamente"));

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;

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
		System.out.println(listPacientes.get(0));
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

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Timestamp getFechaHclinica() {
		return fechaHclinica;
	}

	public void setFechaHclinica(Timestamp fechaHclinica) {
		this.fechaHclinica = fechaHclinica;
	}

	public String getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(String idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(String idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public String getIdDieta() {
		return idDieta;
	}

	public void setIdDieta(String idDieta) {
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

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

}