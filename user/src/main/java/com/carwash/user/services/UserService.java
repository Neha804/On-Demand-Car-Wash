package com.carwash.user.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.carwash.user.controllers.UserController;
import com.carwash.user.models.Customer;
import com.carwash.user.repositories.UserRepository;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
	@Autowired
private UserController controller;
	
	@Autowired
	private UserRepository repository;
	
	public Customer addUser(Customer user) {
		return repository.save(user);
	}
	
	public List<Customer> getUsers() {
		List<Customer> users = repository.findAll();
		System.out.println("Getting data from DB : " + users);
		return users;
	}
	
	public void deleteUser(String emailId) {
		repository.deleteById(emailId);
	}
	
	public void updateUser(String emailId, Customer user) {
		List<Customer> users = repository.findAll();
		for(int i=0;i<users.size();i++) {
			Customer u = users.get(i);
			if(u.getEmailId().equals(emailId)) {
				users.set(i, user);
				return;
			}
		}
	}
	
	public Optional<Customer> getByUserEmail(String emailId) {
		return repository.findByEmail(emailId);
	}
	
	private static Customer u;
	static String  password="default";
	static String user="default";
	public boolean Dcrypto(String n,String o)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
		return encoder.matches(n, o);  
		
	
	}
	public void auth(String a, String b)
	{
		List<Customer> k=  repository.getemailById(a);
		for(Customer t:k)
		{
			password=t.getPassword();
			user=t.getEmailId();
			System.out.println(user+password);
		}
		System.out.println(user+password);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals("username"))
		{
			return new User("username","$2a$12$vW3JUxIwIe2epnIQDUJOL.FFcwgRgnBb2RK.AXKppapGr0A99c1hW",
					new ArrayList<>());
			
		}
		else if (user.equals(username)) {
			return new User(user, password,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
