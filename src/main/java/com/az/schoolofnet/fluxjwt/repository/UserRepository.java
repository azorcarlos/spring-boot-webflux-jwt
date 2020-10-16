package com.az.schoolofnet.fluxjwt.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.az.schoolofnet.fluxjwt.model.User;

import reactor.core.publisher.Mono;


public interface UserRepository extends ReactiveMongoRepository<User, String> {
	Mono<User> findByUsername(String username);

}
