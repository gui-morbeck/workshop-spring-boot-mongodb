package com.centrooleo.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.centrooleo.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {

}
