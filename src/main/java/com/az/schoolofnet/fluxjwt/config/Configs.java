package com.az.schoolofnet.fluxjwt.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.az.schoolofnet.fluxjwt.handle.AuthHandler;
import com.az.schoolofnet.fluxjwt.model.User;



@Configuration
public class Configs {
	
    @Bean
	public RouterFunction<ServerResponse> auth(AuthHandler handler) {
    	return RouterFunctions
    			.route(POST("/sign-up").and(accept(APPLICATION_JSON)), handler::signUp)
    			.andRoute(POST("/login").and(accept(APPLICATION_JSON)), handler::login);
    
    
    }
    
    @Bean
    RouterFunction<ServerResponse> home() {
        return route(GET("/").and(accept(APPLICATION_JSON)), request -> ok().body(fromValue("Home page")));
    }

    @Bean
    RouterFunction<ServerResponse> about() {
        return route(GET("/about").and(accept(APPLICATION_JSON)), request -> ok().body(fromValue("About page")));
    }
    
    @Bean
    RouterFunction<ServerResponse> pageCount(){
    	User us = new User("Azor Carlos", "azorcarlos@gmail.com");
    	us.setUsername("Azor CArlos");
    		
		return route(GET("/count").and(accept(APPLICATION_JSON)), request -> ok().body(fromValue(us)));
    }
    
    
    

}
