package com.project.clinica.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clinica.entity.Usuario;
import com.project.clinica.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario validarUsuario(String login) {
		return repo.iniciarSesion(login);
	}
	
}
