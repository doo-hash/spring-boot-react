package com.example.springwithreactappln.springbootreact.repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springwithreactappln.springbootreact.model.User;

@RestController
public class UserController {

	@Autowired
	private UserRepository userrepo;
	
	@PostMapping("/users")
	public ResponseEntity<User> saveuser(@RequestBody User user) throws URISyntaxException {
		User adduser = userrepo.save(user);
		return ResponseEntity.created(new URI("/users"+adduser.getId())).body(adduser);
	}
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		List<User> users = userrepo.findAll();
		return users;
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Long id) {
		User user = userrepo.findById(id).orElseThrow(RuntimeException::new);
		return user;
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User user) {
		User currentUser = userrepo.findById(id).orElseThrow(RuntimeException::new);
		currentUser.setName(user.getName());
		currentUser.setEmail(user.getEmail());
		currentUser = userrepo.save(user);
		return ResponseEntity.ok(currentUser);
	}

	public ResponseEntity<User> deleteUser(@PathVariable Long id){
		userrepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
