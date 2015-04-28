package com.unbosque.info.bean;

import java.io.Serializable;
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
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.service.PacienteService;

@ManagedBean(name = "pacienteMB")
@SessionScoped
public class PacienteMB implements Serializable {

	private static final long serialVersionUID = -7809396168460749463L;

	private PacienteMB registroSeleccionado;

	@ManagedProperty(value = "#{PacienteService}")
	PacienteService pacienteService;

	List<Paciente> pacienteList;

	private String correo;

	private String estado;

	private Integer id;

	private String sexo;

	private Integer identificacion;

	private String nombresApellidos;

	private String progNutricion;

	private String telefono;

	private String direccion;

	public void addPaciente() {
		try {

			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;

			Paciente paciente = new Paciente();

			paciente.setId(id);
			paciente.setCorreo(correo);
			paciente.setEstado("A");
			paciente.setSexo(sexo);
			paciente.setDireccion(direccion);
			paciente.setProgNutricion(progNutricion);
			paciente.setIdentificacion(identificacion);
			paciente.setTelefono(telefono);
			paciente.setNombresApellidos(nombresApellidos);

			getPacienteService().addPaciente(paciente);
			reset();
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Registro agregado exitosamente.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public String modPaciente(Paciente paciente) {
		try {
			paciente.setId(getId());

			getPacienteService().updatePaciente(paciente);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;

	}

	public String deletePaciente(Paciente paciente) {
		try {
			paciente.setEstado("I");
			getPacienteService().updatePaciente(paciente);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void reset() {
		this.setId(0);
		this.setDireccion("");
		this.setIdentificacion(0);
		this.setNombresApellidos("");
		this.setProgNutricion("");
		this.setTelefono("");
		this.setCorreo("");
		this.setEstado("");
		this.setSexo("");

	}

	public List<Paciente> getPacientesList() {
		pacienteList = new ArrayList<Paciente>();
		pacienteList.addAll(getPacienteService().getPacientes());
		return pacienteList;
	}

	public PacienteService getPacienteService() {
		return pacienteService;
	}

	public void setPacienteService(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}

	public List<Paciente> getPacienteList() {
		return pacienteList;
	}

	public void setPacienteList(List<Paciente> pacienteList) {
		this.pacienteList = pacienteList;
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

}
