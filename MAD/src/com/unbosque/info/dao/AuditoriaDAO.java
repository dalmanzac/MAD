package com.unbosque.info.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Dieta;

@Repository
public class AuditoriaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addAuditoria(Auditoria auditoria) {
		getSessionFactory().getCurrentSession().save(auditoria);

	}

	public List<Auditoria> getAuditorias() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Auditoria").list();
		return list;
	}
}
