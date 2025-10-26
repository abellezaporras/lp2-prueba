package com.project.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clinica.entity.BienHasRequerimiento;
import com.project.clinica.entity.BienHasRequerimientoPK;

public interface BienHasRequerimientoRepository extends 
			JpaRepository<BienHasRequerimiento, BienHasRequerimientoPK>{

}
