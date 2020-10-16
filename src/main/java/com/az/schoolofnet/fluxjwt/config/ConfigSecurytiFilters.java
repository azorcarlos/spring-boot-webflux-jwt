package com.az.schoolofnet.fluxjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ConfigSecurytiFilters {
	
	@Autowired
	private AuthManager authManager;
	
	@Autowired
	private SecurityContext securityContext;
	
	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		
		return http.cors().disable()
				   .csrf().disable()
				   .authenticationManager(authManager)
				   .securityContextRepository(securityContext)
				   .authorizeExchange()
				   .pathMatchers(new String[] {"/login/**","/sign-up/**"}).permitAll()
				   .pathMatchers(HttpMethod.OPTIONS).permitAll()
				   .anyExchange().authenticated()
				   .and()
				   .build();
				   
		
	}
	
	@Bean
	BCryptPasswordEncoder passEncode() {
		return new BCryptPasswordEncoder();
	}

}
