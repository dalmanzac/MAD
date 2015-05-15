package com.unbosque.info.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Parametro;
import com.unbosque.info.entidad.Usuario;
import com.unbosque.info.service.AuditoriaService;
import com.unbosque.info.service.UsuarioService;
import com.unbosque.info.util.CifrarClave;
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
	private Auditoria reportes = new Auditoria();
	private String cla;
	private String user;
	private int numeroEntrada = 0;
	private Usuario sesion;
	private static final Logger logger = Logger.getLogger(UsuarioMB.class);

	public void addUsuario() throws EmailException {

		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);

		try {

			RequestContext context = RequestContext.getCurrentInstance();

			java.util.Date now = new java.util.Date();
			fechaCreacion = new java.sql.Timestamp(now.getTime());
			fechaClave = new java.sql.Timestamp(now.getTime());
			if (!existeLogin(login)) {
				if (Validacion.validarDatoAlfabetico(login)) {
					if (Validacion.validarNombreApellido(apellidosNombres)) {
						if (Validacion.validarEmail(correo)) {
							if (Validacion.validarContraseña(password)) {
								Usuario usuario = new Usuario();

								usuario.setId(id);
								usuario.setCorreo(correo);
								usuario.setEstado("A");
								usuario.setFechaClave(fechaClave);
								usuario.setApellidosNombres(apellidosNombres);
								usuario.setFechaCreacion(fechaCreacion);
								usuario.setLogin(login);
								CifrarClave pass = new CifrarClave();
								String sincifrar = password;
								password = pass.cifradoClave(password);
								usuario.setPassword(password);
								usuario.setTipoUsuario(tipoUsuario);
								setEmail(login, sincifrar, apellidosNombres,
										fechaCreacion, correo, tipoUsuario);

								reportes.setId(reportes.getId());
								reportes.setDescripcion("Se agrego nuevo Usuario");
								reportes.setFechaAuditoria(fechaCreacion);
								reportes.setOperacion("C");
								reportes.setTablaAuditoria("Usuario");
								reportes.setTablaId(usuario.getLogin());
								reportes.setUsuarioId(sesion.getLogin());

								getUsuarioService().addAuditoria(reportes);
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
														"Contraseña Incorrecta. (Mayuscula, Minuscula, Numero por lo Menos)",
														"Contraseña Incorrecta. (Mayuscula, Minuscula, Numero por lo Menos)"));
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
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_WARN,
											"Login Incorrecto, Solo Letras y Primera en Mayúscula.",
											"Login Incorrecto, Solo Letras y Primera en Mayúscula."));
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

		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);

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
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		System.out.println(usuario.getApellidosNombres());
		try {
			java.util.Date now = new java.util.Date();
			fechaCreacion = new java.sql.Timestamp(now.getTime());
			usuario.setEstado("I");

			reportes.setId(reportes.getId());
			reportes.setDescripcion("Se Elimino un Usuario");
			reportes.setFechaAuditoria(fechaCreacion);
			reportes.setOperacion("D");
			reportes.setTablaAuditoria("Usuario");
			reportes.setTablaId(usuario.getLogin());
			reportes.setUsuarioId(sesion.getLogin());

			getUsuarioService().addAuditoria(reportes);
			getUsuarioService().updateUsuario(usuario);
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

	public String desactivarUsuario(Usuario usuario) {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		System.out.println(usuario.getApellidosNombres());
		try {
			java.util.Date now = new java.util.Date();
			fechaCreacion = new java.sql.Timestamp(now.getTime());
			usuario.setEstado("I");

			reportes.setId(reportes.getId());
			reportes.setDescripcion("Se Desactivo un Usuario");
			reportes.setFechaAuditoria(fechaCreacion);
			reportes.setOperacion("D");
			reportes.setTablaAuditoria("Usuario");
			reportes.setTablaId(usuario.getLogin());
			reportes.setUsuarioId(sesion.getLogin());

			getUsuarioService().addAuditoria(reportes);
			getUsuarioService().updateUsuario(usuario);

		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return null;

	}

	public void modNutricionista() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);

		RequestContext context = RequestContext.getCurrentInstance();
		Usuario modNutricionista = sesion;
		java.util.Date now = new java.util.Date();
		fechaCreacion = new java.sql.Timestamp(now.getTime());

		if (password.equals("")) {
			modNutricionista.setPassword(modNutricionista.getPassword());
		} else {
			if (Validacion.validarContraseña(password)) {
				CifrarClave pass = new CifrarClave();
				password = pass.cifradoClave(password);
				modNutricionista.setPassword(password);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Ingrese Contraseña Correctamente.",
								"Ingrese Contraseña Correctamente."));
			}
		}

		if (apellidosNombres.equals("")) {
			modNutricionista.setApellidosNombres(modNutricionista
					.getApellidosNombres());
		} else {
			if (Validacion.validarNombreApellido(apellidosNombres)) {
				modNutricionista.setApellidosNombres(apellidosNombres);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Ingrese Apellido y Nombre Correctamente.",
								"Ingrese Apellido y Nombre Correctamente."));
			}
		}

		if (correo.equals("")) {
			modNutricionista.setCorreo(modNutricionista.getCorreo());
		} else {
			if (Validacion.validarEmail(correo)) {
				modNutricionista.setCorreo(correo);
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
		reportes.setId(reportes.getId());
		reportes.setDescripcion("Se Modifico un Nutricionista");
		reportes.setFechaAuditoria(fechaCreacion);
		reportes.setOperacion("U");
		reportes.setTablaAuditoria("Usuario");
		reportes.setTablaId(modNutricionista.getLogin());
		reportes.setUsuarioId(sesion.getLogin());

		getUsuarioService().addAuditoria(reportes);
		getUsuarioService().updateUsuario(modNutricionista);

	}

	public void modUsuario() {

		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		RequestContext context = RequestContext.getCurrentInstance();
		System.out.println(usuario.toString());
		java.util.Date now = new java.util.Date();
		fechaCreacion = new java.sql.Timestamp(now.getTime());

		if (apellidosNombres.equals("")) {
			usuario.setApellidosNombres(usuario.getApellidosNombres());
		} else {
			if (Validacion.validarNombreApellido(apellidosNombres)) {
				usuario.setApellidosNombres(apellidosNombres);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Ingrese Apellido y Nombre Correctamente.",
								"Ingrese Apellido y Nombre Correctamente."));
			}
		}

		if (login.equals("")) {
			usuario.setLogin(usuario.getLogin());
		} else {
			if (!existeLogin(login)) {
				if (Validacion.validarDatoAlfabetico(login)) {
					usuario.setLogin(login);
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
											"Login Incorrecto, Solo Letras y Primera en Mayúscula.",
											"Login Incorrecto, Solo Letras y Primera en Mayúscula."));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Usuario ya existe!", "Usuario ya existe!"));
			}
		}

		if (correo.equals("")) {
			usuario.setCorreo(usuario.getCorreo());
		} else {
			if (Validacion.validarEmail(correo)) {
				usuario.setCorreo(correo);
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

		if (password.equals("")) {
			usuario.setPassword(usuario.getPassword());
		} else {
			if (Validacion.validarContraseña(password)) {
				CifrarClave pass = new CifrarClave();
				password = pass.cifradoClave(password);
				usuario.setPassword(password);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Modificado Exitosamente",
								"Modificado Exitosamente"));
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Ingrese Contraseña Correctamente.",
								"Ingrese Contraseña Correctamente."));
			}
		}

		if (tipoUsuario.equals("")) {
			usuario.setTipoUsuario(usuario.getTipoUsuario());
		} else {
			usuario.setTipoUsuario(tipoUsuario);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		if (estado.equals("")) {
			usuario.setEstado(usuario.getEstado());
		} else {
			usuario.setEstado(estado);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Modificado Exitosamente",
							"Modificado Exitosamente"));
		}

		reportes.setId(reportes.getId());
		reportes.setDescripcion("Se Modifico un Usuario");
		reportes.setFechaAuditoria(fechaCreacion);
		reportes.setOperacion("U");
		reportes.setTablaAuditoria("Usuario");
		reportes.setTablaId(usuario.getLogin());
		reportes.setUsuarioId(sesion.getLogin());

		getUsuarioService().addAuditoria(reportes);

		reset();
		getUsuarioService().updateUsuario(usuario);

	}

	public void loginId() {

		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		RequestContext context = RequestContext.getCurrentInstance();

		try {
			Usuario temp = getUsuarioService().getUsuarioByUser(login);
			String fechaFinal = temp.getFechaClave().toString();

			if (numeroEntrada == 3) {
				desactivarUsuario(temp);
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Usuario No Activo, Cuenta Bloqueada. Usted lleva "
										+ numeroEntrada + "/3 Intentos.",
								"Usuario No Activo, Cuenta Bloqueada. Usted lleva "
										+ numeroEntrada + "/3 Intentos."));
			} else {
				if (temp.getEstado().equals("A")) {
					CifrarClave clave = new CifrarClave();
					cla = clave.cifradoClave(password);

					if (temp.getPassword().equals(cla)) {
						sesion = temp;
						if (temp.getTipoUsuario().equals("A")) {
							java.util.Date now = new java.util.Date();
							fechaCreacion = new java.sql.Timestamp(
									now.getTime());
							reportes.setId(reportes.getId());
							reportes.setDescripcion("Ingreso al Sistema un Usuario");
							reportes.setFechaAuditoria(fechaCreacion);
							reportes.setOperacion("I");
							reportes.setTablaAuditoria("Usuario");
							reportes.setTablaId(temp.getLogin());
							reportes.setUsuarioId(sesion.getLogin());

							getUsuarioService().addAuditoria(reportes);

							FacesContext.getCurrentInstance()
									.getExternalContext()
									.redirect("UsuarioNewForm.xhtml");

							reset();
						} else if (temp.getTipoUsuario().equals("U")) {
							if (!cambioFecha(fechaFinal)) {
								numeroEntrada = 0;

								java.util.Date now = new java.util.Date();
								fechaCreacion = new java.sql.Timestamp(
										now.getTime());
								reportes.setId(reportes.getId());
								reportes.setDescripcion("Ingreso al sistema un Nutricionista");
								reportes.setFechaAuditoria(fechaCreacion);
								reportes.setOperacion("I");
								reportes.setTablaAuditoria("Usuario");
								reportes.setTablaId(temp.getLogin());
								reportes.setUsuarioId(sesion.getLogin());

								getUsuarioService().addAuditoria(reportes);
								FacesContext.getCurrentInstance()
										.getExternalContext()
										.redirect("PacienteNewForm.xhtml");
								reset();

							} else {
								java.util.Date now = new java.util.Date();
								fechaCreacion = new java.sql.Timestamp(
										now.getTime());
								reportes.setId(reportes.getId());
								reportes.setDescripcion("Se debe Cambiar la Contraseña");
								reportes.setFechaAuditoria(fechaCreacion);
								reportes.setOperacion("I");
								reportes.setTablaAuditoria("Usuario");
								reportes.setTablaId(temp.getLogin());
								reportes.setUsuarioId(sesion.getLogin());

								getUsuarioService().addAuditoria(reportes);
								FacesContext
										.getCurrentInstance()
										.addMessage(
												null,

												new FacesMessage(
														FacesMessage.SEVERITY_WARN,
														"Su fecha ha vencido, debe Cambiarla! ",
														"Su fecha ha vencido, debe Cambiarla! "));
								FacesContext.getCurrentInstance()
										.getExternalContext()
										.redirect("NutricionistaModForm.xhtml");

							}
						}
					} else {
						numeroEntrada++;
						FacesContext.getCurrentInstance().addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_WARN,
										"Revise Clave. Usted lleva "
												+ numeroEntrada
												+ "/3 Intentos.",
										"Revise Clave. Usted lleva "
												+ numeroEntrada
												+ "/3 Intentos."));

					}
				} else {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_WARN,
									"Usuario No Activo", "Usuario No Activo"));

				}
			}

		} catch (Exception e) {

			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "No existe",
							"No existe"));
		}

	}

	public void reset() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
		this.setId(0);
		this.setCorreo("");
		this.setEstado("");
		this.setApellidosNombres("");
		this.setLogin("");
		this.setPassword("");
		this.setTipoUsuario("");
		this.setNumeroEntrada(0);

	}

	public void resetButton() {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
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
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);
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
				+ "\n Contraseña: "
				+ password
				+ "\n  Fecha: "
				+ fechaCreacion
				+ "\n"
				+ "\n Gracias! "
				+ "\n"
				+ "\n"
				+ "Para ingresar a la Pagína por favor copie el siguiente enlace: "
				+ "\n" + "\n" + "http://localhost:8080/MAD/login.xhtml ";
		mail.setMensagem(text);
		mail.setTitulo("Sistema de Gestión de Dietas");
		EmailUtils enviar = new EmailUtils();
		enviar.enviaEmail(mail);
	}

	public boolean cambioFecha(String fechaUsuario) {
		logger.setLevel(Level.ALL);
		logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.FATAL);
		logger.setLevel(Level.OFF);
		logger.setLevel(Level.TRACE);

		List<Parametro> parametro = new ArrayList<Parametro>();
		parametro = getUsuarioService().getParametros();
		Calendar uno = Calendar.getInstance();
		Calendar dos = Calendar.getInstance();

		Date fecha = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fechaSistema = format.format(fecha);

		int diaSistema = Integer.parseInt(fechaSistema.substring(7));
		int mesSistema = Integer.parseInt(fechaSistema.substring(3, 5));
		int anoSistema = Integer.parseInt(fechaSistema.substring(6));

		int dia = Integer.parseInt(fechaUsuario.substring(8, 10));
		int mes = Integer.parseInt(fechaUsuario.substring(5, 7));
		int ano = Integer.parseInt(fechaUsuario.substring(0, 4));

		uno.set(ano, mes, dia);
		dos.set(anoSistema, mesSistema, diaSistema);

		long milis1 = uno.getTimeInMillis();
		long milis2 = dos.getTimeInMillis();
		long diferencia = milis2 - milis1;

		long diferenciaFinal = diferencia / (24 * 60 * 60 * 1000);

		int parametro2 = 0;

		for (int i = 0; i < parametro.size(); i++) {
			if (parametro.get(i).getParametro().contains("Cambiar Contraseña"))
				parametro2 = Integer.parseInt(parametro.get(i).getValor());
		}

		if (diferenciaFinal >= parametro2)
			return true;

		return false;
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

	public String getCla() {
		return cla;
	}

	public void setCla(String cla) {
		this.cla = cla;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
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

	public int getNumeroEntrada() {
		return numeroEntrada;
	}

	public void setNumeroEntrada(int numeroEntrada) {
		this.numeroEntrada = numeroEntrada;
	}

	public Usuario getSesion() {
		return sesion;
	}

	public void setSesion(Usuario sesion) {
		this.sesion = sesion;
	}

	public Auditoria getReportes() {
		return reportes;
	}

	public void setReportes(Auditoria reportes) {
		this.reportes = reportes;
	}

}