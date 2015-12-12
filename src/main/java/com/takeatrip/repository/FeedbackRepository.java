package com.takeatrip.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.takeatrip.domain.Feedback;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, String>{
	@Query("{ '$id' : ?0 }")
	Feedback findById(String id);
}
