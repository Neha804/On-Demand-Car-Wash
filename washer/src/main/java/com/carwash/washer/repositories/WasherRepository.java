package com.carwash.washer.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.carwash.washer.models.Washer;

@Repository
public interface WasherRepository extends MongoRepository<Washer,String>{
	
	@Query("{'washerEmailId' : :#{#washerEmailId}}")
	Optional<Washer> findByEmail(@Param("washerEmailId")String washerEmailId);
	
	@Query("{'location' : :#{#location}")
	List<Washer> findByLocation(@Param("location")String location);

}
