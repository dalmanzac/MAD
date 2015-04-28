package com.unbosque.info.dao;

import java.util.List;

import com.unbosque.info.entidad.Tratamiento;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TratamientoDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addTratamiento(Tratamiento tratamiento) {
		getSessionFactory().getCurrentSession().save(tratamiento);

	}

	public void deleteTratamiento(Tratamiento tratamiento) {
		getSessionFactory().getCurrentSession().delete(tratamiento);
	}

	public void updateTratamiento(Tratamiento tratamiento) {
		getSessionFactory().getCurrentSession().update(tratamiento);
	}

	public Tratamiento getTratamientoById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Tratamiento where id=?").setParameter(0, id)
				.list();
		return (Tratamiento) list.get(0);
	}

	public List<Tratamiento> getTratamientos() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Tratamiento").list();
		return list;
	}

}
