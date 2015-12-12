package com.takeatrip.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.takeatrip.domain.City;
import com.takeatrip.domain.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer,String>{
	@Query("{ '$id' : ?0 }")
	Transfer findById(String id);
	@Query("{ 'cityA.$id' : ?0, 'cityB.$id' : ?1 }")
	List<Transfer> findByCityPair(ObjectId cityA, ObjectId cityB);
	@Query("{ $or: [{ 'cityA.$id' : ?0 }, { 'cityB.$id' : ?0 }] }")
	List<Transfer> getAllWithCity(ObjectId cityId);
}
