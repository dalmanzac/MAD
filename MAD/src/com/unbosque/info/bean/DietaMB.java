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
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.DietaService;

@ManagedBean(name = "dietaMB")
@SessionScoped
public class DietaMB implements Serializable {

	private static final long serialVersionUID = -1L;

	private DietaMB registroSeleccionado;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{DietaService}")
	DietaService dietaService;

	List<Dieta> dietaList;

	private int id;
	private String nombre;
	private String estado;
	private String descripcion;
	private Dieta dieta;

	public void addDieta() {
		try {

			RequestContext context = RequestContext.getCurrentInstance();
			FacesMessage message = null;

			Dieta dieta = new Dieta();

			dieta.setId(id);
			dieta.setEstado("A");
			dieta.setDescripcion(descripcion);
			dieta.setNombre(nombre);

			getDietaService().addDieta(dieta);
			reset();
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "",
					"Registro agregado exitosamente.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public void modDieta() {

	System.out.println(dieta.getNombre());
		try {
			if (nombre.equals("")) {
				dieta.setNombre(dieta.getNombre());
			} else {
				dieta.setNombre(nombre);
			}

				dieta.setEstado("A");
			
			if (descripcion.equals("")) {
				dieta.setDescripcion(dieta.getDescripcion());
			} else {
				dieta.setDescripcion(descripcion);
			}

			reset();
			getDietaService().updateDieta(dieta);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public String deleteDieta(Dieta dieta) {
		try {
			dieta.setEstado("I");
			getDietaService().updateDieta(dieta);
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

	public List<Dieta> getDietasList() {
		dietaList = new ArrayList<Dieta>();
		dietaList.addAll(getDietaService().getDietas());
		return dietaList;
	}

	public DietaService getDietaService() {
		return dietaService;
	}

	public void setDietaService(DietaService dietaService) {
		this.dietaService = dietaService;
	}

	public List<Dieta> getDietaList() {
		return dietaList;
	}

	public void setDietaList(List<Dieta> dietaList) {
		this.dietaList = dietaList;
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

	public void setDieta(Dieta dieta) {
		System.out.println(dieta.toString());
		this.dieta = dieta;
	}

}
