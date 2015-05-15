package com.unbosque.info.service;

import java.util.List;

import com.unbosque.info.dao.PacienteDAO;
import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Paciente;
import com.unbosque.info.entidad.Parametro;
import com.unbosque.info.entidad.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PacienteService")
@Transactional(readOnly = true)
public class PacienteService {

	// PacienteDAO is injected...
	@Autowired
	PacienteDAO pacienteDAO;

	@Transactional(readOnly = false)
	public void addPaciente(Paciente paciente) {
		getPacienteDAO().addPaciente(paciente);
	}
	
	@Transactional(readOnly = false)
	public void addAuditoria(Auditoria auditoria) {
		getPacienteDAO().addAuditoria(auditoria);
	}
	
	@Transactional(readOnly = false)
	public Paciente getPacienteByUser(String nombre) {
		return getPacienteDAO().getPacienteByUser(nombre);
	}

	@Transactional(readOnly = false)
	public void deletePaciente(Paciente paciente) {
		getPacienteDAO().deletePaciente(paciente);
	}

	@Transactional(readOnly = false)
	public void updatePaciente(Paciente paciente) {
		getPacienteDAO().updatePaciente(paciente);
	}

	public Paciente getPacienteById(int id) {
		return getPacienteDAO().getPacienteById(id);
	}

	public List<Paciente> getPacientes() {
		return getPacienteDAO().getPacientes();
	}

	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}
}
