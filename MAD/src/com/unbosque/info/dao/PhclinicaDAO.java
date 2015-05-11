package com.unbosque.info.dao;

import java.util.List;

import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.entidad.Phclinica;
import com.unbosque.info.entidad.Tratamiento;
import com.unbosque.info.entidad.Usuario;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhclinicaDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Phclinica getPhclinicaByNombre(String nombre) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Phclinica where nombre=?")
				.setParameter(0, nombre).list();
		return (Phclinica) list.get(0);
	}

	public List<Dieta> getDietaByNombre() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Dieta").list();

		return list;
	}
	
	public List<Tratamiento> getTratamientoByNombre() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Tratamiento").list();

		return list;
	}
	
	public List<Enfermedad> getEnfermedadByNombre() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Enfermedad").list();

		return list;
	}
	
	public List<Paciente> getPacienteByNombre() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Paciente").list();

		return list;
	}

	public void addPhclinica(Phclinica phclinica) {
		getSessionFactory().getCurrentSession().save(phclinica);

	}

	public void deletePhclinica(Phclinica phclinica) {
		getSessionFactory().getCurrentSession().delete(phclinica);
	}

	public void updatePhclinica(Phclinica phclinica) {
		getSessionFactory().getCurrentSession().update(phclinica);
	}

	public Phclinica getPhclinicaById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Phclinica where id=?").setParameter(0, id)
				.list();
		return (Phclinica) list.get(0);
	}

	public List<Phclinica> getPhclinicas() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Phclinica").list();
		return list;
	}

}