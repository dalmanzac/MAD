package com.unbosque.info.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Parametro;

@Repository
public class ParametroDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addParametro(Parametro parametro) {
		getSessionFactory().getCurrentSession().save(parametro);

	}
	
	public void addAuditoria(Auditoria auditoria) {
		getSessionFactory().getCurrentSession().save(auditoria);

	}

	public void deleteParametro(Parametro parametro) {
		getSessionFactory().getCurrentSession().delete(parametro);
	}

	public void updateParametro(Parametro parametro) {
		getSessionFactory().getCurrentSession().update(parametro);
	}

	public Parametro getParametroById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Parametro where id=?").setParameter(0, id)
				.list();
		return (Parametro) list.get(0);
	}

	public List<Parametro> getParametros() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Parametro").list();
		return list;
	}

}
