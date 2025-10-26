package com.project.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clinica.entity.BienHasRequerimiento;
import com.project.clinica.entity.BienHasRequerimientoPK;
import com.project.clinica.entity.Requerimiento;
import com.project.clinica.repository.BienHasRequerimientoRepository;
import com.project.clinica.repository.RequerimientoRepository;

import jakarta.transaction.Transactional;

@Service
public class RequerimientoServices {
	@Autowired
	private RequerimientoRepository repoReque;
	
	@Autowired
	private BienHasRequerimientoRepository repoDet;
	
	@Transactional
	public void registrar(Requerimiento obj) {
		try {
			//grabar requerimiento
			repoReque.save(obj);
			//recuperar detalle
			List<BienHasRequerimiento>detalle=obj.getDetalle();
			//bucle para realizar recorrido sobre detalle
			detalle.forEach(det->{
				BienHasRequerimientoPK id=new BienHasRequerimientoPK();
				id.setCod_reque(obj.getCodigo());
				id.setCod_bien(det.getBien().getCodigo());
				det.setPk(id);
			});
			repoDet.saveAll(detalle);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
}
