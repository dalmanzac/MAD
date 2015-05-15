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
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Tratamiento;
import com.unbosque.info.service.TratamientoService;
import com.unbosque.info.util.Validacion;

@ManagedBean(name = "tratamientoMB")
@SessionScoped
public class TratamientoMB implements Serializable {

	private static final long serialVersionUID = -7809396168460749463L;

	private TratamientoMB registroSeleccionado;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{TratamientoService}")
	TratamientoService tratamientoService;

	List<Tratamiento> tratamientoList;

	private int id;
	private String nombre;
	private String estado;
	private String descripcion;
	private Tratamiento tratamiento;
	private Auditoria reportes = new Auditoria();
	private static final Logger logger = Logger.getLogger(TratamientoMB.class);

	public void addTratamiento() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		try {

			RequestContext context = RequestContext.getCurrentInstance();

			if (!existeTratamiento(nombre)) {
				if (Validacion.validarDatoAlfabetico(nombre)) {

					Tratamiento tratamiento = new Tratamiento();

					tratamiento.setId(id);
					tratamiento.setEstado("A");
					tratamiento.setDescripcion(descripcion);
					tratamiento.setNombre(nombre);

					java.util.Date now = new java.util.Date();
					Timestamp fechaCreacion = new java.sql.Timestamp(
							now.getTime());
					reportes.setId(reportes.getId());
					reportes.setDescripcion("Se Agrego Tratamiento");
					reportes.setFechaAuditoria(fechaCreacion);
					reportes.setOperacion("C");
					reportes.setTablaAuditoria("Tratamiento");
					reportes.setTablaId(tratamiento.getNombre());
					reportes.setUsuarioId("Administrador");

					getTratamientoService().addAuditoria(reportes);

					getTratamientoService().addTratamiento(tratamiento);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Agregado Exitosamente",
									"Agregado Exitosamente"));
					reset();

				} else {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_WARN,
											"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula).",
											"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula)."));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Tratamiento ya existe!",
								"Tratamiento ya existe!"));
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public void modTratamiento() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);

		System.out.println(tratamiento.toString());

		if (nombre.equals("")) {
			tratamiento.setNombre(tratamiento.getNombre());
		} else {
			if (!existeTratamiento(nombre)) {
				if (Validacion.validarDatoAlfabetico(nombre)) {
					tratamiento.setNombre(nombre);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Modificado Exitosamente",
									"Modificado Exitosamente"));
				} else {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_WARN,
											"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula).",
											"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula)."));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Tratamiento ya existe!",
								"Tratamiento ya existe!"));
			}
		}
		if (estado.equals("")) {
			tratamiento.setEstado(tratamiento.getEstado());
		} else {
			tratamiento.setEstado(estado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (descripcion.equals("")) {
			tratamiento.setDescripcion(tratamiento.getDescripcion());
		} else {
			tratamiento.setDescripcion(descripcion);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		java.util.Date now = new java.util.Date();
		Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
		reportes.setId(reportes.getId());
		reportes.setDescripcion("Se Modifico Tratamiento");
		reportes.setFechaAuditoria(fechaCreacion);
		reportes.setOperacion("U");
		reportes.setTablaAuditoria("Tratamiento");
		reportes.setTablaId(tratamiento.getNombre());
		reportes.setUsuarioId("Administrador");

		getTratamientoService().addAuditoria(reportes);

		reset();
		getTratamientoService().updateTratamiento(tratamiento);

	}

	// Aqui colocamos el de borrado
	public String deleteTratamiento(Tratamiento tratamiento) {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		try {

			tratamiento.setEstado("I");

			java.util.Date now = new java.util.Date();
			Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
			reportes.setId(reportes.getId());
			reportes.setDescripcion("Se Elimino Tratamiento");
			reportes.setFechaAuditoria(fechaCreacion);
			reportes.setOperacion("D");
			reportes.setTablaAuditoria("Tratamiento");
			reportes.setTablaId(tratamiento.getNombre());
			reportes.setUsuarioId("Administrador");

			getTratamientoService().addAuditoria(reportes);
			getTratamientoService().updateTratamiento(tratamiento);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Inactivo Exitosamente", "Inactivo Exitosamente"));
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
		this.setNombre("");
		this.setEstado("");
		this.setDescripcion("");

	}

	public boolean existeTratamiento(String nombre) {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);

		try {
			Tratamiento temp = getTratamientoService().getTratamientoByNombre(
					nombre);

			if (temp != null) {
				return true;
			}

		} catch (Exception e) {

		}
		return false;

	}

	public List<Tratamiento> getTratamientosList() {
		tratamientoList = new ArrayList<Tratamiento>();

		tratamientoList.addAll(getTratamientoService().getTratamientos());

		return tratamientoList;
	}

	public TratamientoService getTratamientoService() {
		return tratamientoService;
	}

	public void setTratamientoService(TratamientoService tratamientoService) {
		this.tratamientoService = tratamientoService;
	}

	public void setTratamientoList(List<Tratamiento> tratamientoList) {
		this.tratamientoList = tratamientoList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Tratamiento> getTratamientoList() {
		return tratamientoList;
	}

	public TratamientoMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(TratamientoMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		System.out.println(tratamiento.toString());
		this.tratamiento = tratamiento;
	}

	public Auditoria getReportes() {
		return reportes;
	}

	public void setReportes(Auditoria reportes) {
		this.reportes = reportes;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

}