package com.project.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clinica.entity.Bien;
import com.project.clinica.repository.BienRepository;

@Service
public class BienServices {
	@Autowired
	private BienRepository repo;
	
	public List<Bien> findAllByTipoBien(int cod){
		return repo.listarPorTipoBien(cod);
	}
	
}
