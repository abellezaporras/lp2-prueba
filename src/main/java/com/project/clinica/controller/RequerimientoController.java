package com.project.clinica.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clinica.controller.dto.Detalle;
import com.project.clinica.entity.Bien;
import com.project.clinica.entity.BienHasRequerimiento;
import com.project.clinica.entity.Requerimiento;
import com.project.clinica.services.BienServices;
import com.project.clinica.services.RequerimientoServices;
import com.project.clinica.services.TipoBienServices;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/reque")
public class RequerimientoController {
	@Autowired
	private TipoBienServices servicioTipo;
	@Autowired
	private BienServices servicioBien;
	@Autowired
	private RequerimientoServices servicioReque;
	
	
	@RequestMapping("/list")
	private String lista(Model model) {
		model.addAttribute("tipos",servicioTipo.findAll());
		
		return "requerimiento";
	}
	
	@RequestMapping("/consultaTipoBien")
	@ResponseBody
	public List<Bien> consulta(@RequestParam("codigo") int cod){
		return servicioBien.findAllByTipoBien(cod);
	}
	@RequestMapping("/adicionar")
	@ResponseBody
	public List<Detalle> adiconarItem(@RequestParam("codigo") int codigo,@RequestParam("nombre") String nombre,
							 @RequestParam("cantidad") int cantidad,
							 HttpServletRequest request) {
		//declarar arreglo de objetos de la clase Detalle
		List<Detalle> lista=null;
		//validar si existe el atributo de tipo sesión "datos"
		if(request.getSession().getAttribute("datos")==null) {
			//crear lista
			lista=new ArrayList<Detalle>();
		}
		else
			//recuperar el atributo de sesión datos y enviarlo a lista
			lista=(List<Detalle>) request.getSession().getAttribute("datos");
		
		//crear objeto de la clase Detalle
		Detalle bean=new Detalle();
		//setear
		bean.setCodigo(codigo);
		bean.setNombre(nombre);
		bean.setCantidad(cantidad);
		//adicionar objeto "bean" dentro del arreglo lista
		lista.add(bean);
		//crear el atributo de tipo sesión datos
		request.getSession().setAttribute("datos", lista);
		
		return lista;
	}
	@RequestMapping("/eliminar")
	@ResponseBody
	public List<Detalle> eliminarItem(@RequestParam("codigo") int cod,HttpServletRequest request){
		List<Detalle> lista=(List<Detalle>) request.getSession().getAttribute("datos");
		lista.removeIf(bean->bean.getCodigo()==cod);
		return lista;
	}
	@RequestMapping("/grabar")
	public String grabar(@RequestParam("fecha") String fec,@RequestParam("nombre") String nom,HttpServletRequest request,
			RedirectAttributes redirect) {
		try {
			//crear objeto de la entidad Requerimiento
			Requerimiento bean=new Requerimiento();
			//setear
			bean.setNombreRequerimiento(nom);
			bean.setFecha(LocalDate.parse(fec));
			bean.setEstado("CREADO");
			bean.setCodigoUsuario(1);
			
			List<BienHasRequerimiento> data=new ArrayList<BienHasRequerimiento>();
			//recuperar valores de la sesión datos
			List<Detalle> lista=(List<Detalle>) request.getSession().getAttribute("datos");
			//bucle para realizar recorrido sobre lista
			lista.forEach(det->{
				BienHasRequerimiento bhr=new BienHasRequerimiento();
				//crear objeto de la entidad Bien
				Bien b=new Bien();
				b.setCodigo(det.getCodigo());
				bhr.setBien(b);
				bhr.setCantidad(det.getCantidad());
				data.add(bhr);
			});
			//enviar el arreglo data dentro del objeto bean 
			bean.setDetalle(data);
			//
			lista.clear();
			request.getSession().setAttribute("datos", lista);
			//grabar
			servicioReque.registrar(bean);
			redirect.addFlashAttribute("MENSAJE","Requerimiento registrado");
		} catch (Exception e) {
			redirect.addFlashAttribute("MENSAJE","Error en el registro de requerimiento");
			e.printStackTrace();
		}
		return "redirect:/reque/list";
	}
	
}





