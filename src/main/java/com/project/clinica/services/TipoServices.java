package com.project.clinica.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clinica.entity.Tipo;
import com.project.clinica.repository.TipoRepository;

@Service
public class TipoServices {
	//inyección de dependencias
	@Autowired
	private TipoRepository repo;
	
	public void registrarTipo(Tipo bean) {
		repo.save(bean);//bean no tiene codigo=0 ===>INSERT INTO
	}
	public void actualizarTipo(Tipo bean) {
		repo.save(bean);//bean debe tener todo: código  nombre ===>UPDATE
	}
	public Tipo buscarPorId(Integer cod) {
		return repo.findById(cod).orElse(null); //====>Select ... where llave_primaria=?
	}
	public void eliminarPorId(Integer cod) {
		repo.deleteById(cod);//====>delete ....where llave_primaria=?
	}
	public List<Tipo> listarTodos(){
		return repo.findAll();//=====> select *from tabla
	}
}
