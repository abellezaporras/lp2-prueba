package com.project.clinica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clinica.entity.Medicamento;
import com.project.clinica.services.MedicamentoServices;
import com.project.clinica.services.TipoServices;

@Controller
@RequestMapping("/med")
public class MedicamentoController {
	@Autowired
	private MedicamentoServices medServicio;
	
	@Autowired
	private TipoServices tipoServicio;
	
	
	@RequestMapping("/list")
	public String lista(Model response) {
		response.addAttribute("medicamentos",medServicio.listar());
		response.addAttribute("tipos",tipoServicio.listarTodos());
		response.addAttribute("medi",new Medicamento());
		return "medicamento";
	}
	@RequestMapping("/save")
	public String registrar(@ModelAttribute("medi") Medicamento bean,RedirectAttributes redirect) {
		try {
			//validar atributo codigo del paràmetro bean
			if(bean.getCodigo()==null) {
				medServicio.registrar(bean);
				//atributo de tipo flash
				redirect.addFlashAttribute("MENSAJE","Medicamento registrado");
			}
			else {
				medServicio.actualizar(bean);
				redirect.addFlashAttribute("MENSAJE","Medicamento actualizado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/med/list";
	}
	@RequestMapping("/find")/// /find?codigo=8
	@ResponseBody
	public Medicamento buscar(@RequestParam("codigo")Integer cod) {
		return medServicio.buscarPorId(cod);
	}
	@RequestMapping("/delete")
	public String eliminar(@RequestParam("codigo")Integer cod,RedirectAttributes redirect) {
		try {
			medServicio.eliminarPorId(cod);
			redirect.addFlashAttribute("MENSAJE","Medicamento eliminado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/med/list";
	}
	
}
















