package com.unbosque.info.dao;

import java.util.List;

import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Usuario;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnfermedadDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Enfermedad getEnfermedadByNombre(String nombre) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Enfermedad where nombre=?").setParameter(0, nombre)
				.list();
		return (Enfermedad) list.get(0);
	}

	public void addEnfermedad(Enfermedad enfermedad) {
		getSessionFactory().getCurrentSession().save(enfermedad);

	}
	
	public void addAuditoria(Auditoria auditoria) {
		getSessionFactory().getCurrentSession().save(auditoria);

	}

	public void deleteEnfermedad(Enfermedad enfermedad) {
		getSessionFactory().getCurrentSession().delete(enfermedad);
	}

	public void updateEnfermedad(Enfermedad enfermedad) {
		getSessionFactory().getCurrentSession().update(enfermedad);
	}

	public Enfermedad getEnfermedadById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Enfermedad where id=?").setParameter(0, id)
				.list();
		return (Enfermedad) list.get(0);
	}

	public List<Enfermedad> getEnfermedades() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Enfermedad").list();
		return list;
	}

}
