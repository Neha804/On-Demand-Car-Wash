package com.carwash.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carwash.user.models.User;
import com.carwash.user.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public User addUser(User user) {
		return repository.save(user);
	}
	
	public List<User> getUsers() {
		List<User> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	
	public void deleteUser(String emailId) {
		repository.deleteById(emailId);
	}
	
	public void updateUser(String emailId, User user) {
		List<User> users = repository.findAll();
		for(int i=0;i<users.size();i++) {
			User u = users.get(i);
			if(u.getEmailId().equals(emailId)) {
				users.set(i, user);
				return;
			}
		}
	}
	
	public Optional<User> getByUserEmail(String emailId) {
		return repository.findByEmail(emailId);
	}
}
