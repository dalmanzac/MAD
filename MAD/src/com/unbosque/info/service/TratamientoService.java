package com.unbosque.info.service;

import java.util.List;

import com.unbosque.info.dao.TratamientoDAO;
import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Enfermedad;
import com.unbosque.info.entidad.Tratamiento;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("TratamientoService")
@Transactional(readOnly = true)
public class TratamientoService {

	// TratamientoDAO is injected...
	@Autowired
	TratamientoDAO tratamientoDAO;

	@Transactional(readOnly = false)
	public void addTratamiento(Tratamiento tratamiento) {
		getTratamientoDAO().addTratamiento(tratamiento);
	}

	@Transactional(readOnly = false)
	public void deleteTratamiento(Tratamiento tratamiento) {
		getTratamientoDAO().deleteTratamiento(tratamiento);
	}

	@Transactional(readOnly = false)
	public void updateTratamiento(Tratamiento tratamiento) {
		getTratamientoDAO().updateTratamiento(tratamiento);
	}

	public Tratamiento getTratamientoById(int id) {
		return getTratamientoDAO().getTratamientoById(id);
	}
	
	@Transactional(readOnly = false)
	public void addAuditoria(Auditoria auditoria) {
		getTratamientoDAO().addAuditoria(auditoria);
	}
	
	@Transactional(readOnly = false)
	public Tratamiento getTratamientoByNombre(String nombre) {
		return getTratamientoDAO().getTratamientoByNombre(nombre);
	}

	public List<Tratamiento> getTratamientos() {
		return getTratamientoDAO().getTratamientos();
	}

	public TratamientoDAO getTratamientoDAO() {
		return tratamientoDAO;
	}

	public void setTratamientoDAO(TratamientoDAO tratamientoDAO) {
		this.tratamientoDAO = tratamientoDAO;
	}
}
