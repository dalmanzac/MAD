package com.unbosque.info.dao;

import java.util.List;
import com.unbosque.info.entidad.Paciente;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PacienteDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addPaciente(Paciente paciente) {
		getSessionFactory().getCurrentSession().save(paciente);

	}

	public void deletePaciente(Paciente paciente) {
		getSessionFactory().getCurrentSession().delete(paciente);
	}

	public void updatePaciente(Paciente paciente) {
		getSessionFactory().getCurrentSession().update(paciente);
	}

	public Paciente getPacienteById(int id) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Paciente where id=?").setParameter(0, id)
				.list();
		return (Paciente) list.get(0);
	}

	public List<Paciente> getPacientes() {
		List list = getSessionFactory().getCurrentSession()
				.createQuery("from Paciente").list();
		return list;
	}

}
