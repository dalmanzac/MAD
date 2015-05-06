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
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Tratamiento;
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.EnfermedadService;
import com.unbosque.info.service.TratamientoService;
import com.unbosque.info.util.Validacion;

@ManagedBean(name = "enfermedadMB")
@SessionScoped
public class EnfermedadMB implements Serializable {

	private static final long serialVersionUID = -7809396168460749463L;

	private EnfermedadMB registroSeleccionado;

	@ManagedProperty(value = "#{EnfermedadService}")
	EnfermedadService enfermedadService;

	List<Enfermedad> enfermedadList;

	private int id;
	private String nombre;
	private String estado;
	private String descripcion;
	private Enfermedad enfermedad;

	public void addEnfermedad() {
		try {

			RequestContext context = RequestContext.getCurrentInstance();

			if (!existeEnfermedad(nombre)) {
				if (Validacion.validarDatoAlfabetico(nombre)) {

					Enfermedad enfermedad = new Enfermedad();

					enfermedad.setId(id);
					enfermedad.setEstado("A");
					enfermedad.setDescripcion(descripcion);
					enfermedad.setNombre(nombre);

					getEnfermedadService().addEnfermedad(enfermedad);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Agregada Exitosamente",
									"Agregada Exitosamente"));
					reset();

				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula).",
									"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula)."));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Enfermedad ya existe!",
								"Enfermedad ya existe!"));
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public void modEnfermedad() {

		RequestContext context = RequestContext.getCurrentInstance();
		System.out.println(enfermedad.toString());

		if (nombre.equals("")) {
			enfermedad.setNombre(enfermedad.getNombre());
		} else {
			if (!existeEnfermedad(nombre)) {
				if (Validacion.validarDatoAlfabetico(nombre)) {
					enfermedad.setNombre(nombre);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Modificada Exitosamente",
									"Modificada Exitosamente"));
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula).",
									"Nombre Incorrecto (Sin Espacios y Primer letra en Mayúscula)."));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Enfermedad ya existe!",
								"Enfermedad ya existe!"));
			}
		}
		if (estado.equals("")) {
			enfermedad.setEstado(enfermedad.getEstado());
		} else {
			enfermedad.setEstado(estado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}
		

		if (descripcion.equals("")) {
			enfermedad.setDescripcion(enfermedad.getDescripcion());
		} else {
			enfermedad.setDescripcion(descripcion);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificada Exitosamente",
							"Modificada Exitosamente"));
		}

		reset();
		getEnfermedadService().updateEnfermedad(enfermedad);

	}

	public String deleteEnfermedad(Enfermedad enfermedad) {
		try {
			enfermedad.setEstado("I");
			getEnfermedadService().updateEnfermedad(enfermedad);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Inactiva Exitosamente",
							"Inactiva Exitosamente"));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void reset() {
		this.setId(0);
		this.setDescripcion("");
		this.setEstado("");
		this.setNombre("");

	}

	public boolean existeEnfermedad(String nombre) {

		try {
			Enfermedad temp = getEnfermedadService().getEnfermedadByNombre(
					nombre);

			if (temp != null) {
				return true;
			}

		} catch (Exception e) {

		}
		return false;

	}

	public List<Enfermedad> getEnfermedadesList() {
		enfermedadList = new ArrayList<Enfermedad>();

		enfermedadList.addAll(getEnfermedadService().getEnfermedades());

		return enfermedadList;
	}

	public EnfermedadService getEnfermedadService() {
		return enfermedadService;
	}

	public void setEnfermedadService(EnfermedadService enfermedadService) {
		this.enfermedadService = enfermedadService;
	}

	public void setEnfermedadList(List<Enfermedad> enfermedadList) {
		this.enfermedadList = enfermedadList;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		System.out.println(enfermedad.toString());
		this.enfermedad = enfermedad;
	}

}
