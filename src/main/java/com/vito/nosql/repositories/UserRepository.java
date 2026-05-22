package com.vito.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vito.nosql.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
}
