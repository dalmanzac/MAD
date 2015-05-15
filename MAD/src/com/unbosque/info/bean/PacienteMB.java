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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.PacienteService;
import com.unbosque.info.util.Validacion;

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

	private Paciente paciente;

	private Auditoria reportes = new Auditoria();

	private static final Logger logger = Logger.getLogger(PacienteMB.class);

	public void addPaciente() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		try {

			RequestContext context = RequestContext.getCurrentInstance();

			if (!existeCedula(identificacion)) {
				if (Validacion.validarNombreApellido(nombresApellidos)) {
					if (Validacion.validarDatoNumerico(telefono)) {
						if (Validacion.validarEmail(correo)) {
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

							java.util.Date now = new java.util.Date();
							Timestamp fechaCreacion = new java.sql.Timestamp(
									now.getTime());
							reportes.setId(reportes.getId());
							reportes.setDescripcion("Se Agrego Paciente");
							reportes.setFechaAuditoria(fechaCreacion);
							reportes.setOperacion("C");
							reportes.setTablaAuditoria("Paciente");
							reportes.setTablaId(paciente.getNombresApellidos());
							reportes.setUsuarioId("Administrador");

							getPacienteService().addAuditoria(reportes);

							getPacienteService().addPaciente(paciente);
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_INFO,
											"Agregado Exitosamente",
											"Agregado Exitosamente"));
							reset();

						} else {
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_WARN,
											"Correo Incorrecto. ",
											"Correo Incorrecto. "));
						}
					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"Telefono Incorrecto. (Solo Numeros)",
										"Telefono Incorrecto. (Solo Numeros)"));
					}
				} else {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_WARN,
											"Ingrese Apellido y Nombre Correctamente.",
											"Ingrese Apellido y Nombre Correctamente."));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Usuario ya existe!", "Usuario ya existe!"));
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public void modPaciente() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		RequestContext context = RequestContext.getCurrentInstance();
		System.out.println(paciente.toString());

		if (nombresApellidos.equals("")) {
			paciente.setNombresApellidos(paciente.getNombresApellidos());
		} else {
			if (Validacion.validarNombreApellido(nombresApellidos)) {
				paciente.setNombresApellidos(nombresApellidos);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Nombre y Apellido Incorrecto",
								"Nombre y Apellido Incorrecto."));
			}

		}

		if (identificacion.equals("")) {
			paciente.setIdentificacion(paciente.getIdentificacion());
		} else {
			if (!existeCedula(identificacion)) {
				paciente.setIdentificacion(identificacion);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Cedula ya existe", "Cedula ya existe."));
			}

		}

		if (estado.equals("")) {
			paciente.setEstado(paciente.getEstado());
		} else {
			paciente.setEstado(estado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (telefono.equals("")) {
			paciente.setTelefono(paciente.getTelefono());
		} else {
			if (Validacion.validarDatoNumerico(telefono)) {
				paciente.setTelefono(telefono);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Telefono Incorrecto (Solo Numeros)",
								"Telefono Incorrecto (Solo Numeros) "));
			}

		}

		if (correo.equals("")) {
			paciente.setCorreo(paciente.getCorreo());
		} else {
			if (Validacion.validarEmail(correo)) {
				paciente.setCorreo(correo);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Correo Incorrecto", "Correo Incorrecto."));
			}
		}

		if (progNutricion.equals("")) {
			paciente.setProgNutricion(paciente.getProgNutricion());
		} else {
			paciente.setProgNutricion(progNutricion);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (sexo.equals("")) {
			paciente.setSexo(paciente.getSexo());
		} else {
			paciente.setSexo(sexo);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (direccion.equals("")) {
			paciente.setDireccion(paciente.getDireccion());
		} else {
			paciente.setDireccion(direccion);
		}

		java.util.Date now = new java.util.Date();
		Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
		reportes.setId(reportes.getId());
		reportes.setDescripcion("Se Modifico Paciente");
		reportes.setFechaAuditoria(fechaCreacion);
		reportes.setOperacion("U");
		reportes.setTablaAuditoria("Paciente");
		reportes.setTablaId(paciente.getNombresApellidos());
		reportes.setUsuarioId("Administrador");

		getPacienteService().addAuditoria(reportes);
		getPacienteService().updatePaciente(paciente);
		reset();
	}

	public String deletePaciente(Paciente paciente) {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		try {
			paciente.setEstado("I");

			java.util.Date now = new java.util.Date();
			Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
			reportes.setId(reportes.getId());
			reportes.setDescripcion("Se Elimino Paciente");
			reportes.setFechaAuditoria(fechaCreacion);
			reportes.setOperacion("D");
			reportes.setTablaAuditoria("Paciente");
			reportes.setTablaId(paciente.getNombresApellidos());
			reportes.setUsuarioId("Administrador");

			getPacienteService().addAuditoria(reportes);
			getPacienteService().updatePaciente(paciente);
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

	public void reset() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		this.setId(0);
		this.setDireccion("");
		this.setNombresApellidos("");
		this.setProgNutricion("");
		this.setTelefono("");
		this.setCorreo("");
		this.setEstado("");
		this.setSexo("");
		this.setIdentificacion(0);

	}

	public boolean existeCedula(int cedula) {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);

		try {
			Paciente temp = getPacienteService().getPacienteById(cedula);

			if (temp != null) {
				return true;
			}

		} catch (Exception e) {

		}
		return false;

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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		System.out.println(paciente.toString());
		this.paciente = paciente;
	}

	public Auditoria getReportes() {
		return reportes;
	}

	public void setReportes(Auditoria reportes) {
		this.reportes = reportes;
	}

}
