package com.vito.nosql.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vito.nosql.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
