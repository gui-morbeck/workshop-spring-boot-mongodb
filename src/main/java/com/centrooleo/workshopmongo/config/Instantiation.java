package com.centrooleo.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.centrooleo.workshopmongo.domain.Post;
import com.centrooleo.workshopmongo.domain.User;
import com.centrooleo.workshopmongo.dto.AuthorDTO;
import com.centrooleo.workshopmongo.dto.CommentDTO;
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));		
		
		Post post1 = new Post(null, sdf.parse("2018-03-21"), "Partiu viagem", "Vou viajar p SP!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("2018-03-23"), "Bom dia!", "Acordei feliz hoje.", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(maria);
		
		CommentDTO c1 = new CommentDTO("Aproveite!", Instant.now(), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Boa viagem!", Instant.now(), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", Instant.now(), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
	}

}
