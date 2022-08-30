package com.carwash.user.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carwash.user.models.Customer;

@Repository
public interface UserRepository extends MongoRepository<Customer,String> {
	
	@Query("{'emailId' : :#{#emailId}}")//key value pairs
	//match all the documents containing that emailid
	Optional<Customer> findByEmail(@Param("emailId")String emailId);
	@Query("{email : ?0}")                            // SQL Equivalent : SELECT * FROM BOOK where author = ?
    List<Customer> getemailById(String emailId);

//	public User findByName(String firstName);
//	public User findByEmail(String emailId);
//	public User findByLocation(String location);
}