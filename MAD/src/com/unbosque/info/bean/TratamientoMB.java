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

	public void addTratamiento() {
		try {

			RequestContext context = RequestContext.getCurrentInstance();
			
			if (!existeTratamiento(nombre)) {
				if (Validacion.validarDatoAlfabetico(nombre)) {

		
			Tratamiento tratamiento = new Tratamiento();

			tratamiento.setId(id);
			tratamiento.setEstado("A");
			tratamiento.setDescripcion(descripcion);
			tratamiento.setNombre(nombre);

			getTratamientoService().addTratamiento(tratamiento);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Agregado Exitosamente",
							"Agregado Exitosamente"));
			reset();
		

				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Nombre Incorrecto (Sin Espacios).",
									"Nombre Incorrecto (Sin Espacios)."));
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
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(FacesMessage.SEVERITY_WARN,
											"Nombre Incorrecto (Sin Espacios).",
											"Nombre Incorrecto (Sin Espacios)."));
						}

					} else {
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"Tratamiento ya existe!",
										"Tratamiento ya existe!"));
					}
				}

					tratamiento.setEstado("A");
				
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

				reset();
				getTratamientoService().updateTratamiento(tratamiento);

			} 

		


	// Aqui colocamos el de borrado
	public String deleteTratamiento(Tratamiento tratamiento) {
		try {

			tratamiento.setEstado("I");
			getTratamientoService().updateTratamiento(tratamiento);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Inactivo Exitosamente",
							"Inactivo Exitosamente"));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void reset() {
		this.setId(0);
		this.setNombre("");
		this.setEstado("");
		this.setDescripcion("");

	}
	
	public boolean existeTratamiento(String nombre) {

		try {
			Tratamiento temp = getTratamientoService().getTratamientoByNombre(nombre);

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

}