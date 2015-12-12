package com.takeatrip.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.takeatrip.domain.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer,String>{
	@Query("{ '$id' : ?0 }")
	Transfer findById(String id);
}
