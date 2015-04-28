package com.unbosque.info.dao;

import java.util.List;
import com.unbosque.info.entidad.Enfermedad;
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

	public void addEnfermedad(Enfermedad enfermedad) {
		getSessionFactory().getCurrentSession().save(enfermedad);

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
