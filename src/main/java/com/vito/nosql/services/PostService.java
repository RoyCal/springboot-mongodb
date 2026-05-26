package com.vito.nosql.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vito.nosql.domain.Post;
import com.vito.nosql.domain.User;
import com.vito.nosql.repositories.PostRepository;
import com.vito.nosql.repositories.UserRepository;
import com.vito.nosql.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Post insert(Post obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		Post post = findById(id);
		
		String authorId = post.getAuthor().getId();
		
		repo.deleteById(id);
		
		User user = userRepo.findById(authorId).orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));
		
		user.getPosts().removeIf(p -> p.getId().equals(id));
		
		userRepo.save(user);
	}
	
	public List<Post> findAll(){
		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Post não encontrado"));
	}
	
	public List<Post> findByTitle(String text){
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
//	public List<Post> findByTitle(String text){
//		return repo.searchTitle(text);
//	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 24*60*60*1000);
		return repo.fullSearch(text, minDate, maxDate);
	}

}
