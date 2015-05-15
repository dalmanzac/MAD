package com.unbosque.info.bean;

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
import com.unbosque.info.entidad.Parametro;
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.ParametroService;
import com.unbosque.info.service.UsuarioService;
import com.unbosque.info.util.Validacion;

@ManagedBean(name = "parametroMB")
@SessionScoped
public class ParametroMB {

	private static final long serialVersionUID = -1L;

	private ParametroMB registroSeleccionado;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{ParametroService}")
	ParametroService parametroService;

	List<Parametro> parametroList;

	private int id;
	private String modulo;
	private String parametro;
	private String valor;
	private String estado;
	private Parametro parametro2;
	private Auditoria reportes = new Auditoria();
	private static final Logger logger = Logger.getLogger(ParametroMB.class);

	public void addParametro() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		RequestContext context = RequestContext.getCurrentInstance();
		Parametro parametro3 = new Parametro();

		parametro3.setId(id);
		parametro3.setModulo(modulo);
		parametro3.setParametro(parametro);
		parametro3.setValor(valor);
		parametro3.setEstado("A");

		java.util.Date now = new java.util.Date();
		Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
		reportes.setId(reportes.getId());
		reportes.setDescripcion("Se Agrego Parametro");
		reportes.setFechaAuditoria(fechaCreacion);
		reportes.setOperacion("C");
		reportes.setTablaAuditoria("Parametro");
		reportes.setTablaId(parametro3.getParametro());
		reportes.setUsuarioId("Administrador");

		getParametroService().addAuditoria(reportes);

		getParametroService().addParametro(parametro3);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Agregado Exitosamente", "Agregado Exitosamente"));
		reset();
	}

	public void modParametro() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		RequestContext context = RequestContext.getCurrentInstance();
		System.out.println(parametro2.toString());

		if (valor.equals("")) {
			parametro2.setValor(parametro2.getValor());
		} else {
			if (Validacion.validarDatoNumerico(valor)) {
				parametro2.setValor(valor);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Ingrese Valor Correctamente.",
								"Ingrese Valor Correctamente."));
			}
		}

		if (estado.equals("")) {
			parametro2.setEstado(parametro2.getEstado());
		} else {
			parametro2.setEstado(estado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));

		}

		java.util.Date now = new java.util.Date();
		Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
		reportes.setId(reportes.getId());
		reportes.setDescripcion("Se Modifico Parametro");
		reportes.setFechaAuditoria(fechaCreacion);
		reportes.setOperacion("U");
		reportes.setTablaAuditoria("Parametro");
		reportes.setTablaId(parametro2.getParametro());
		reportes.setUsuarioId("Administrador");

		getParametroService().addAuditoria(reportes);

		getParametroService().updateParametro(parametro2);
		reset();
	}

	public void reset() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		this.setEstado("");
		this.setModulo("");
		this.setParametro("");
		this.setValor("");
	}

	public String deleteParametro(Parametro parametro) {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		System.out.println(parametro.getParametro());
		try {

			parametro.setEstado("I");

			java.util.Date now = new java.util.Date();
			Timestamp fechaCreacion = new java.sql.Timestamp(now.getTime());
			reportes.setId(reportes.getId());
			reportes.setDescripcion("Se Elimino Parametro");
			reportes.setFechaAuditoria(fechaCreacion);
			reportes.setOperacion("D");
			reportes.setTablaAuditoria("Parametro");
			reportes.setTablaId(parametro.getParametro());
			reportes.setUsuarioId("Administrador");

			getParametroService().addAuditoria(reportes);
			getParametroService().updateParametro(parametro);
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

	public List<Parametro> getParametrosList() {
		parametroList = new ArrayList<Parametro>();

		parametroList.addAll(getParametroService().getParametros());

		return parametroList;
	}

	public ParametroMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(ParametroMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

	public ParametroService getParametroService() {
		return parametroService;
	}

	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

	public List<Parametro> getParametroList() {
		return parametroList;
	}

	public void setParametroList(List<Parametro> parametroList) {
		this.parametroList = parametroList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Parametro getParametro2() {
		return parametro2;
	}

	public void setParametro2(Parametro parametro2) {
		this.parametro2 = parametro2;
	}

	public Auditoria getReportes() {
		return reportes;
	}

	public void setReportes(Auditoria reportes) {
		this.reportes = reportes;
	}

}
