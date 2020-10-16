package com.az.schoolofnet.fluxjwt.handle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.az.schoolofnet.fluxjwt.Utils.JwtUtil;
import com.az.schoolofnet.fluxjwt.model.User;
import com.az.schoolofnet.fluxjwt.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class AuthHandler {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;

	public AuthHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Mono<ServerResponse> signUp(ServerRequest req) {
		Mono<User> userMono = req.bodyToMono(User.class);
		return userMono.map(u -> {
			User userPass = new User(u.getUsername(), u.getPassword());
			userPass.setPassword(this.encoder.encode(u.getPassword()));
			return userPass;
		}).flatMap(this.userRepository::save).flatMap(user -> ServerResponse.ok().body(BodyInserters.fromValue(user)));

	}

	public Mono<ServerResponse> login(ServerRequest req) {
		Mono<User> userMono = req.bodyToMono(User.class);
		return userMono
				.flatMap(u -> this.userRepository.findByUsername(u.getUsername())
				.flatMap(user ->{
					System.out.println(u.getPassword());
					System.out.println(user.getPassword());
			if (this.encoder.matches(u.getPassword(),user.getPassword())) {
				System.out.println(u.getPassword());
				System.out.println(user.getPassword());
				return ServerResponse.ok().body(BodyInserters.fromValue(jwtUtil.genToken(user)));
			} else {
				System.out.println(u.getPassword());
				System.out.println(user.getPassword());
				return ServerResponse.badRequest().body(BodyInserters.fromValue("Invalid Credentials"));
			}
		}).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromValue("User not exists"))));

	}
}
