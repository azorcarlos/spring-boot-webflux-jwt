package com.az.schoolofnet.fluxjwt.Utils;

import java.io.Serializable;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.az.schoolofnet.fluxjwt.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getUserName(String token) {
	
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public Claims getAllClaimsFromToken(String token) {
		
		return Jwts.parser()
				.setSigningKey("MY_SECRET")
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String genToken(User user) {
		return Jwts.builder()
				.setSubject(user.getUsername())
				.signWith(SignatureAlgorithm.HS256, "MY_SECRET")
				.compact();
		
	}
}
