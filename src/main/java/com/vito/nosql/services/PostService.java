package com.vito.nosql.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vito.nosql.domain.Comment;
import com.vito.nosql.domain.Post;
import com.vito.nosql.domain.User;
import com.vito.nosql.dto.AuthorDTO;
import com.vito.nosql.dto.CommentDTO;
import com.vito.nosql.dto.PostDTO;
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
		Post savedPost = repo.save(obj);

	    User user = userRepo.findById(savedPost.getAuthor().getId())
	            .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

	    savedPost = repo.findById(savedPost.getId()).get();

	    user.getPosts().add(savedPost);

	    userRepo.save(user);

	    return savedPost;
	}
	
	public void delete(String id) {
		Post post = findById(id);
		
		String authorId = post.getAuthor().getId();
		
		User user = userRepo.findById(authorId).orElseThrow(() -> new ObjectNotFoundException("Autor não encontrado"));
		
		user.getPosts().removeIf(p -> p.getId().equals(id));
		
		userRepo.save(user);
		
		repo.deleteById(id);
	}
	
	public Comment insertComment(String id, Comment obj) {
		Post post = findById(id);
		
		post.getComments().add(obj);
		
		repo.save(post);
		
		return obj;
	}
	
	public void deleteComment(String postId, String commentId) {
		Post post = findById(postId);
		
		post.getComments().removeIf(p -> p.getId().equals(commentId));
		
		repo.save(post);
	}
	
	public List<Post> findAll(){
		return repo.findAll();
	}
	
	public List<Comment> findAll(String postId){
		Post post = findById(postId);
		return post.getComments();
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
	
	public Post fromDTO(PostDTO objDto) {
		User user = userRepo.findById(objDto.getAuthorId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		return new Post(null, new Date(), objDto.getTitle(), objDto.getBody(), new AuthorDTO(user));
	}
	
	public Comment fromDto(CommentDTO objDto) {
		User user = userRepo.findById(objDto.getAuthorId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
		
		return new Comment(null, objDto.getText(), new Date(), new AuthorDTO(user));
	}

}
