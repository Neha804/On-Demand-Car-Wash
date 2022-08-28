package com.carwash.user.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carwash.user.models.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
	
	@Query("{'emailId' : :#{#emailId}}")//key value pairs
	//match all the documents containing that emailid
	Optional<User> findByEmail(@Param("emailId")String emailId);

//	public User findByName(String firstName);
//	public User findByEmail(String emailId);
//	public User findByLocation(String location);
}