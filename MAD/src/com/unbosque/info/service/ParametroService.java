package com.unbosque.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unbosque.info.dao.ParametroDAO;
import com.unbosque.info.dao.TratamientoDAO;
import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Parametro;
import com.unbosque.info.entidad.Tratamiento;

@Service("ParametroService")
@Transactional(readOnly = true)
public class ParametroService {

	// TratamientoDAO is injected...
	@Autowired
	ParametroDAO parametroDAO;

	@Transactional(readOnly = false)
	public void addParametro(Parametro parametro) {
		getParametroDAO().addParametro(parametro);
	}

	@Transactional(readOnly = false)
	public void deleteParametro(Parametro parametro) {
		getParametroDAO().deleteParametro(parametro);
	}
	
	@Transactional(readOnly = false)
	public void addAuditoria(Auditoria auditoria) {
		getParametroDAO().addAuditoria(auditoria);
	}

	@Transactional(readOnly = false)
	public void updateParametro(Parametro parametro) {
		getParametroDAO().updateParametro(parametro);
	}

	public Parametro getParametroById(int id) {
		return getParametroDAO().getParametroById(id);
	}

	public List<Parametro> getParametros() {
		return getParametroDAO().getParametros();
	}

	public ParametroDAO getParametroDAO() {
		return parametroDAO;
	}

	public void setParametroDAO(ParametroDAO parametroDAO) {
		this.parametroDAO = parametroDAO;
	}
}
