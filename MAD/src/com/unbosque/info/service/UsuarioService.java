package com.unbosque.info.service;

import java.util.List;

import com.unbosque.info.dao.UsuarioDAO;
import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Parametro;
import com.unbosque.info.entidad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UsuarioService")
@Transactional(readOnly = true)
public class UsuarioService {

	// UsuarioDAO is injected...
	@Autowired
	UsuarioDAO usuarioDAO;

	@Transactional(readOnly = false)
	public void addUsuario(Usuario usuario) {
		getUsuarioDAO().addUsuario(usuario);
	}
	
	@Transactional(readOnly = false)
	public void addAuditoria(Auditoria auditoria) {
		getUsuarioDAO().addAuditoria(auditoria);
	}

	@Transactional(readOnly = false)
	public void deleteUsuario(Usuario usuario) {
		getUsuarioDAO().deleteUsuario(usuario);
	}
	
	@Transactional(readOnly = false)
	public void modUsuario(Usuario usuario) {
		getUsuarioDAO().modUsuario(usuario);
	}

	@Transactional(readOnly = false)
	public void updateUsuario(Usuario usuario) {
		getUsuarioDAO().updateUsuario(usuario);
	}

	
	public Usuario getUsuarioById(int id) {
		return getUsuarioDAO().getUsuarioById(id);
	}
	
	@Transactional(readOnly = false)
	public Usuario getUsuarioByUser(String usuario) {
		return getUsuarioDAO().getUsuarioByUser(usuario);
	}

	@Transactional(readOnly = false)
	public List<Usuario> getUsuarios() {
		return getUsuarioDAO().getUsuarios();
	}

	@Transactional(readOnly = false)
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	@Transactional(readOnly = false)
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	@Transactional(readOnly = false)
	public List<Parametro> getParametros() {
		return getUsuarioDAO().getParametros();
	}
}
