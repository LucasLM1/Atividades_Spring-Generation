package com.generation.lojagames.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private  UserDetailsServiceImpl serviceImpl;
	
	//realizando autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
		auth.userDetailsService(serviceImpl);
		
	}
	
	//retornando senha criptografada
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/usuario/cadastrar").permitAll()
		.antMatchers("/usuario/login").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
		
	}
	
	

}
