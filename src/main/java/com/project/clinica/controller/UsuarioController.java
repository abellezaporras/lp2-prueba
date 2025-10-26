package com.project.clinica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	@RequestMapping("/intranet")
	public String intranet() {
		
		return "intranet";
	}
	
}
