package com.centrooleo.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.centrooleo.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

}
