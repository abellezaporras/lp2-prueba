package com.project.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clinica.entity.Medicamento;
import com.project.clinica.repository.MedicamentoRepository;

@Service
public class MedicamentoServices {
	@Autowired
	private MedicamentoRepository repo;
	
	public List<Medicamento> listar(){
		return repo.findAll();
	}
	public void registrar(Medicamento bean) {
		repo.save(bean);
	}
	public void actualizar(Medicamento bean) {
		repo.save(bean);
	}
	public Medicamento buscarPorId(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	public void eliminarPorId(Integer cod) {
		repo.deleteById(cod);
	}
	
}
