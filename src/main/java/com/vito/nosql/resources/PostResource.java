package com.vito.nosql.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vito.nosql.domain.Comment;
import com.vito.nosql.domain.Post;
import com.vito.nosql.dto.CommentDTO;
import com.vito.nosql.dto.PostDTO;
import com.vito.nosql.resources.util.URL;
import com.vito.nosql.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@GetMapping
	public ResponseEntity<List<Post>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(defaultValue = "") String text,
			@RequestParam(defaultValue = "") String minDate, @RequestParam(defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@RequestBody PostDTO objDto){
		Post post = service.fromDTO(objDto);
		post = service.insert(post);
		
		URI uri	= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{id}/comments")
	public ResponseEntity<List<Comment>> findAllComments(@PathVariable String id){
		return ResponseEntity.ok().body(service.findAll(id));
	}
	
	@PostMapping(value = "/{id}/comments")
	public ResponseEntity<Comment> insertComment(@PathVariable String id, @RequestBody CommentDTO objDto){
		Comment comment = service.fromDto(objDto);
		
		comment = service.insertComment(id, comment);
		
		URI uri	= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{postId}/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(@PathVariable String postId, @PathVariable String commentId){
		service.deleteComment(postId, commentId);
		return ResponseEntity.noContent().build();
	}

}
