package com.carwash.user.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.carwash.user.models.Combined;
import com.carwash.user.models.Order;
import com.carwash.user.models.Customer;
import com.carwash.user.repositories.UserRepository;
import com.carwash.user.services.UserService;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/getall")
	public List<Customer> getUsers(){
		return service.getUsers();
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> insertUser(@RequestBody Customer customer) {
		System.out.println("User registered");
		service.addUser(customer);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteuser/{emailId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("emailId")String emailId){
		service.deleteUser(emailId);
		return new ResponseEntity<String>("Delete-successfully",HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateuser/{emailId}")
	public void updateUser(@RequestBody Customer customer,@PathVariable String emailId) {
		service.updateUser(emailId, customer);
	}
	
	@GetMapping("/getemail/{emailId}")
	public Optional<Customer> getByEmailUser(@PathVariable("emailId") String emailId) {
		return service.getByUserEmail(emailId);
	}
	
	@GetMapping("/fullorder/{emailId}")
	public Combined fullOrder(@PathVariable ("emailId") String emailId) {
		//combined is non primitive data type
		RestTemplate restTemplate = new RestTemplate();
		Order orderRecord = restTemplate.getForObject("http://localhost:8083/order/getemail/{emailId}",Order.class,emailId);
//		Optional<User>  userModel= service.getByUserEmail(emailId);
//		User user = userModel.get();
//		Combined c = new Combined(user.getFirstName(),user.getLastName(),user.getEmailId(),user.getLocation(),user.getPassword(),user.getCar(),orderRecord.getOrderId(),orderRecord.getOrderDate());
		Optional<Customer>  userModel= service.getByUserEmail(emailId);
		Customer customer = userModel.get();
		Combined userOrderDetails = new Combined(customer.getFirstName(),customer.getLastName(),customer.getEmailId(),customer.getLocation(),customer.getPassword(),customer.getCar(),orderRecord.getOrderId(),orderRecord.getOrderDate());
		return userOrderDetails;
		
		
	}
}
