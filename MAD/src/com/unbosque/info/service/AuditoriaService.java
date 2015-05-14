package com.unbosque.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unbosque.info.dao.AuditoriaDAO;
import com.unbosque.info.dao.DietaDAO;
import com.unbosque.info.entidad.Auditoria;
import com.unbosque.info.entidad.Dieta;
import com.unbosque.info.entidad.Usuario;

@Service("AuditoriaService")
@Transactional(readOnly = true)
public class AuditoriaService {

	// DietaDAO is injected...
	@Autowired
	AuditoriaDAO auditoriaDAO;

	@Transactional(readOnly = false)
	public void addAuditoria(Auditoria auditoria) {
		getAuditoriaDAO().addAuditoria(auditoria);
	}

	@Transactional(readOnly = false)
	public List<Auditoria> getAuditorias() {
		return getAuditoriaDAO().getAuditorias();
	}

	public AuditoriaDAO getAuditoriaDAO() {
		return auditoriaDAO;
	}

	public void setAuditoriaDAO(AuditoriaDAO auditoriaDAO) {
		this.auditoriaDAO = auditoriaDAO;
	}

}
