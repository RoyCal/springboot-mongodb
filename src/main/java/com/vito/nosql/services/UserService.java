package com.vito.nosql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vito.nosql.domain.Comment;
import com.vito.nosql.domain.Post;
import com.vito.nosql.domain.User;
import com.vito.nosql.dto.UserDTO;
import com.vito.nosql.repositories.PostRepository;
import com.vito.nosql.repositories.UserRepository;
import com.vito.nosql.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PostRepository postRepo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		
		List<Post> posts = postRepo.findAll();
		
		for (Post post : posts) {
			if(post.getAuthor().getId().equals(obj.getId())) {
				post.getAuthor().setName(obj.getName());
				post.getAuthor().setEmail(obj.getEmail());
			}
			
			for (Comment comment : post.getComments()) {
				if(comment.getAuthor().getId().equals(obj.getId())) {
					comment.getAuthor().setName(obj.getName());
					comment.getAuthor().setEmail(obj.getEmail());
				}
			}
		}
		
		postRepo.saveAll(posts);
		
		return repo.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
