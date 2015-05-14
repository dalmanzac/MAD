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

import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.entidad.Tratamiento;
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.DietaService;
import com.unbosque.info.util.Validacion;

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
	private Auditoria reportes = new Auditoria();

	public void addDieta() {
		try {

			RequestContext context = RequestContext.getCurrentInstance();

			if (!existeDieta(nombre)) {
				if (Validacion.validarDatoAlfabetico(nombre)) {

					Dieta dieta = new Dieta();

					dieta.setId(id);
					dieta.setEstado("A");
					dieta.setDescripcion(descripcion);
					dieta.setNombre(nombre);

					java.util.Date now = new java.util.Date();
					Timestamp fechaCreacion = new java.sql.Timestamp(
							now.getTime());
					reportes.setId(reportes.getId());
					reportes.setDescripcion("Se agrego nueva Dieta");
					reportes.setFechaAuditoria(fechaCreacion);
					reportes.setOperacion("C");
					reportes.setTablaAuditoria("Dieta");
					reportes.setTablaId(dieta.getNombre());
					reportes.setUsuarioId("Administrador");

					getDietaService().addAuditoria(reportes);

					getDietaService().addDieta(dieta);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Agregada Exitosamente",
									"Agregada Exitosamente"));
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
								"Dieta ya existe!", "Dieta ya existe!"));
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public void modDieta() {

		System.out.println(dieta.toString());

		if (nombre.equals("")) {
			dieta.setNombre(dieta.getNombre());
		} else {
			if (!existeDieta(nombre)) {
				if (Validacion.validarDatoAlfabetico(nombre)) {
					dieta.setNombre(nombre);
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Modificada Exitosamente",
									"Modificada Exitosamente"));
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
								"Dieta ya existe!", "Dieta ya existe!"));
			}
		}

		if (estado.equals("")) {
			dieta.setEstado(dieta.getEstado());
		} else {
			dieta.setEstado(estado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (descripcion.equals("")) {
			dieta.setDescripcion(dieta.getDescripcion());
		} else {
			dieta.setDescripcion(descripcion);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificada Exitosamente",
							"Modificada Exitosamente"));
		}

		java.util.Date now = new java.util.Date();
		Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
		reportes.setId(reportes.getId());
		reportes.setDescripcion("Se Modifico Dieta");
		reportes.setFechaAuditoria(fechaCreacion);
		reportes.setOperacion("U");
		reportes.setTablaAuditoria("Dieta");
		reportes.setTablaId(dieta.getNombre());
		reportes.setUsuarioId("Administrador");

		getDietaService().addAuditoria(reportes);

		reset();
		getDietaService().updateDieta(dieta);

	}

	public String deleteDieta(Dieta dieta) {
		try {
			dieta.setEstado("I");

			java.util.Date now = new java.util.Date();
			Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
			reportes.setId(reportes.getId());
			reportes.setDescripcion("Se Elimino Dieta");
			reportes.setFechaAuditoria(fechaCreacion);
			reportes.setOperacion("D");
			reportes.setTablaAuditoria("Dieta");
			reportes.setTablaId(dieta.getNombre());
			reportes.setUsuarioId("Administrador");

			getDietaService().addAuditoria(reportes);
			getDietaService().updateDieta(dieta);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Inactiva Exitosamente", "Inactiva Exitosamente"));
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

	public boolean existeDieta(String nombre) {

		try {
			Dieta temp = getDietaService().getDietaByNombre(nombre);

			if (temp != null) {
				return true;
			}

		} catch (Exception e) {

		}
		return false;

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

	public Auditoria getReportes() {
		return reportes;
	}

	public void setReportes(Auditoria reportes) {
		this.reportes = reportes;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setId(int id) {
		this.id = id;
	}

}
