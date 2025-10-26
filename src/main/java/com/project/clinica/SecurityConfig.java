package com.project.clinica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.clinica.security.UserSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserSecurity userSecurity;
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    
		http.authorizeHttpRequests(auth->auth
			.requestMatchers("/usuario/login").permitAll()	
			.anyRequest().authenticated()	
	    )
		.formLogin(form->form
			.loginPage("/usuario/login")
			.loginProcessingUrl("/usuario/login")
			.defaultSuccessUrl("/usuario/intranet",true)
			.failureUrl("/usuario/login?error=true")
			.permitAll()
		)
		.logout(logout->logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/usuario/login?logout=true")
		);
		

        return http.build();
    }
	
	@Bean
	public AuthenticationManager configure(AuthenticationConfiguration auth) throws Exception {
         return auth.getAuthenticationManager();
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
