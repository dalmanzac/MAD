package com.unbosque.info.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.UsuarioService;
import com.unbosque.info.util.EmailUtils;
import com.unbosque.info.util.IndexController;
import com.unbosque.info.util.Validacion;
import com.unbosque.info.entidad.Mensagem;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = -7809396168460749463L;

	private UsuarioMB registroSeleccionado;

	// Spring Customer Service is injected...
	@ManagedProperty(value = "#{UsuarioService}")
	UsuarioService usuarioService;

	List<Usuario> usuarioList;

	private int id;
	private String correo;
	private String estado;
	private String apellidosNombres;
	private Timestamp fechaClave;
	private Timestamp fechaCreacion;
	private int idProyecto;
	private String login;
	private String password;
	private String tipoUsuario;
	private Usuario usuario;

	public void addUsuario() throws EmailException {
		try {

			RequestContext context = RequestContext.getCurrentInstance();

			java.util.Date now = new java.util.Date();
			fechaCreacion = new java.sql.Timestamp(now.getTime());
			fechaClave = new java.sql.Timestamp(now.getTime());
			if (!existeLogin(login)) {
				if (Validacion.validarDatoAlfabetico(login)) {
					if (Validacion.validarNombreApellido(apellidosNombres)) {
						if (Validacion.validarEmail(correo)) {
							if (Validacion.validarContrase�a(password)) {
								Usuario usuario = new Usuario();

								usuario.setId(id);
								usuario.setCorreo(correo);
								usuario.setEstado("A");
								usuario.setFechaClave(fechaClave);
								usuario.setApellidosNombres(apellidosNombres);
								usuario.setFechaCreacion(fechaCreacion);
								usuario.setLogin(login);
								usuario.setPassword(password);
								usuario.setTipoUsuario(tipoUsuario);
								setEmail(login, password, apellidosNombres,
										fechaCreacion, correo, tipoUsuario);
								getUsuarioService().addUsuario(usuario);

								FacesContext.getCurrentInstance().addMessage(
										null,
										new FacesMessage(
												FacesMessage.SEVERITY_INFO,
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
														"Contrase�a Incorrecta. (Mayuscula, Minuscula, Numero por lo Menos)",
														"Contrase�a Incorrecta. (Mayuscula, Minuscula, Numero por lo Menos)"));
							}

						} else {
							FacesContext.getCurrentInstance().addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_WARN,
											"Correo Incorrecto",
											"Correo Incorrecto."));
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
									"Login Incorrecto, Solo Letras",
									"Login Incorrecto, Solo Letras"));
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

	public boolean existeLogin(String login) {

		try {
			Usuario temp = getUsuarioService().getUsuarioByUser(login);

			if (temp != null) {
				return true;
			}

		} catch (Exception e) {

		}
		return false;

	}

	// Aqui colocamos el de borrado
	public String deleteUsuario(Usuario usuario) {
		System.out.println(usuario.getApellidosNombres());
		try {

			usuario.setEstado("I");
			getUsuarioService().updateUsuario(usuario);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void modUsuario() {

		RequestContext context = RequestContext.getCurrentInstance();
		System.out.println(usuario.toString());
		try {
			if (apellidosNombres.equals("")) {
				usuario.setApellidosNombres(usuario.getApellidosNombres());
			} else {
				//if (Validacion.validarNombreApellido(apellidosNombres)) {
					usuario.setApellidosNombres(apellidosNombres);
			//	} else {
//					FacesContext
//							.getCurrentInstance()
//							.addMessage(
//									null,
//									new FacesMessage(
//											FacesMessage.SEVERITY_WARN,
//											"Ingrese Apellido y Nombre Correctamente.",
//											"Ingrese Apellido y Nombre Correctamente."));
		//		}
			}

			if (login.equals("")) {
				usuario.setLogin(usuario.getLogin());
			} else {
				usuario.setLogin(login);
			}

			if (correo.equals("")) {
				usuario.setCorreo(usuario.getCorreo());
			} else {
				usuario.setCorreo(correo);
			}

			if (password.equals("")) {
				usuario.setPassword(usuario.getPassword());
			} else {
				usuario.setPassword(password);
			}

			if (tipoUsuario.equals("")) {
				usuario.setTipoUsuario(usuario.getTipoUsuario());
			} else {
				usuario.setTipoUsuario(tipoUsuario);
			}

			usuario.setEstado("A");

			reset();
			getUsuarioService().updateUsuario(usuario);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

	public void loginId() {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			Usuario temp = getUsuarioService().getUsuarioByUser(login);
			if (temp.getPassword().equals(password)) {
				if (temp.getEstado().equals("A")) {
					if (temp.getTipoUsuario().equals("A")) {

						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("UsuarioNewForm.xhtml");
					} else if (temp.getTipoUsuario().equals("U")) {

						FacesContext.getCurrentInstance().getExternalContext()
								.redirect("PacienteNewForm.xhtml");

					}
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Usuario No Activo", "Usuario No Activo"));
				}

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Revise Clave", "Revise Clave"));
			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "No existe",
							"No existe"));
		}

	}

	public void reset() {
		this.setId(0);
		this.setCorreo("");
		this.setEstado("A");
		this.setApellidosNombres("");
		this.setLogin("");
		this.setPassword("");
		this.setTipoUsuario("");

	}

	public void resetButton() {
		this.setId(0);
		this.setCorreo("");
		this.setEstado("A");
		this.setApellidosNombres("");
		this.setLogin("");
		this.setPassword("");
		this.setTipoUsuario("");

		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("UsuarioNewForm.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setEmail(String usuario, String password,
			String apellidosNombres, java.util.Date fechaCreacion,
			String correo, String tipoUsuario) throws EmailException {
		if (tipoUsuario.equals("U")) {
			tipoUsuario = "Nutricionista";
		} else if (tipoUsuario.equals("A")) {
			tipoUsuario = "Administrador";
		}
		Mensagem mail = new Mensagem();
		mail.setDestino(correo);
		String text = apellidosNombres
				+ "\n"
				+ "\n"
				+ "\n Bienvenido. Estos son sus datos de registro: "
				+ "\n"
				+ "\n"
				+ tipoUsuario
				+ "\n"
				+ "\n"
				+ "Usuario: "
				+ usuario
				+ "\n Contrase�a: "
				+ password
				+ "\n  Fecha: "
				+ fechaCreacion
				+ "\n"
				+ "\n Gracias! "
				+ "\n"
				+ "\n"
				+ "Para ingresar a la Pag�na por favor copie el siguiente enlace: "
				+ "\n" + "\n" + "http://localhost:8080/MAD/login.xhtml ";
		mail.setMensagem(text);
		mail.setTitulo("Sistema de Gesti�n de Dietas");
		EmailUtils enviar = new EmailUtils();
		enviar.enviaEmail(mail);
	}

	public List<Usuario> getUsuariosList() {
		usuarioList = new ArrayList<Usuario>();

		usuarioList.addAll(getUsuarioService().getUsuarios());

		return usuarioList;
	}

	public List<Usuario> getUsuarios() {
		usuarioList = new ArrayList<Usuario>();

		usuarioList.addAll(getUsuarioService().getUsuarios());

		return usuarioList;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getApellidosNombres() {
		return apellidosNombres;
	}

	public void setApellidosNombres(String apellidosNombres) {
		this.apellidosNombres = apellidosNombres;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaClave() {
		return fechaClave;
	}

	public void setFechaClave(Timestamp fechaClave) {
		this.fechaClave = fechaClave;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public String getLogin() {
		return login;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		System.out.println(usuario.toString());
		this.usuario = usuario;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public UsuarioMB getRegistroSeleccionado() {
		return registroSeleccionado;
	}

	public void setRegistroSeleccionado(UsuarioMB registroSeleccionado) {
		this.registroSeleccionado = registroSeleccionado;
	}

}