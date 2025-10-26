package com.project.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.clinica.entity.Tipo;
import com.project.clinica.services.TipoServices;

@Controller
@RequestMapping("/tipo")//ruta o endpoint para acceder a la clase
public class TipoController {
	@Autowired
	private TipoServices tipo;
	
	@RequestMapping("/new") //ruta o endpoint para acceder al método
	public String registrar() {
		try {
			Tipo obj=new Tipo();			
			obj.setCodigo(0);
			obj.setNombre("Prueba01");
			tipo.registrarTipo(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/update") 
	public String actualizar() {
		try {
			Tipo obj=new Tipo();			
			obj.setCodigo(2);
			obj.setNombre("hola");
			tipo.actualizarTipo(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/delete") 
	public String eliminar() {
		try {
			tipo.eliminarPorId(17);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/list") 
	public String listar() {
		List<Tipo> data=tipo.listarTodos();
		for(Tipo bean:data) {
			System.out.println(bean.getNombre());
		}
		
		return "";
	}
	
}
