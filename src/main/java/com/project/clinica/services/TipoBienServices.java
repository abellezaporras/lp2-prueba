package com.project.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clinica.entity.TipoBien;
import com.project.clinica.repository.TipoBienRepository;

@Service
public class TipoBienServices {
	@Autowired
	private TipoBienRepository repo;
	
	public List<TipoBien> findAll(){
		return repo.findAll();
	}
	
}
