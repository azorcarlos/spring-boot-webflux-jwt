package com.az.schoolofnet.fluxjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.az.schoolofnet.fluxjwt.Utils.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class AuthManager implements ReactiveAuthenticationManager{

	@Autowired
	private JwtUtil jwtUtils;
	
	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		String token = authentication.getCredentials().toString();
		String username = this.jwtUtils.getUserName(token);
		if(username != null) {
			UsernamePasswordAuthenticationToken auth =  new UsernamePasswordAuthenticationToken(username, username);
			return Mono.just(auth);
		}else {
			return Mono.empty();
		}

	}

}
