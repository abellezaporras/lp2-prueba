package com.project.clinica.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.clinica.entity.Usuario;
import com.project.clinica.services.UsuarioServices;

@Service
public class UserSecurity implements UserDetailsService{

	@Autowired
	private UsuarioServices servicio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario bean=servicio.validarUsuario(username);
		if(bean==null)
			throw new UsernameNotFoundException("Usuario no existe");
				
		return new User(username , bean.getClave(),
				Collections.singleton(new SimpleGrantedAuthority(bean.getRol().getDescripcion()))); 
				
				
	}

}
