package com.centrooleo.workshopmongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.centrooleo.workshopmongo.domain.Post;
import com.centrooleo.workshopmongo.domain.User;
import com.centrooleo.workshopmongo.dto.AuthorDTO;
import com.centrooleo.workshopmongo.repositories.PostRepository;
import com.centrooleo.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));		
		
		Post post1 = new Post(null, Instant.now(), "Partiu viagem", "Vou viajar p SP!", new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.now(), "Bom dia!", "Acordei feliz hoje.", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
