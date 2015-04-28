package com.unbosque.info.dao;

import java.util.List;
import com.unbosque.info.entidad.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().save(usuario);

	}

	public void deleteUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().delete(usuario);
	}
	
	public void modUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().update(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		getSessionFactory().getCurrentSession().update(usuario);
	}

	public Usuario getUsuarioById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Usuario where id=?").setParameter(0, id)
				.list();
		return (Usuario) list.get(0);
	}

	public Usuario getUsuarioByUser(String usuario) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Usuario where login=?")
				.setParameter(0, usuario).list();

		return (Usuario) list.get(0);
	}

	public List<Usuario> getUsuarios() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Usuario").list();
		return list;
	}

}
