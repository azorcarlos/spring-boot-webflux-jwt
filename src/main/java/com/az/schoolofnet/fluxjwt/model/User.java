package com.az.schoolofnet.fluxjwt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class User {
	@Id
	private String id;
	private String username;
	private String password;
	
	public User() {
		
	}
	
	public User(String id, String username, String password) {
		super();
		this.id       = id;
		this.username = username;
		this.password = password;
	}
	
	public User( String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	

}
