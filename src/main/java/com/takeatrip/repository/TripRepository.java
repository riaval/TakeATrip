package com.takeatrip.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.takeatrip.domain.Trip;

@Repository
public interface TripRepository extends CrudRepository<Trip,String>{
	@Query("{ '$id' : ?0 }")
	Trip findById(String id);
}
