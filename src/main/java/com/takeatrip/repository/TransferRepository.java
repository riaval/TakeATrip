package com.takeatrip.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.takeatrip.domain.City;
import com.takeatrip.domain.Transfer;

@Repository
public interface TransferRepository extends CrudRepository<Transfer,String>{
	@Query("{ '$id' : ?0 }")
	Transfer findById(String id);
	@Query("{ 'cityA' : ?0, 'cityB' : ?1 }")
	List<Transfer> findByCityPair(City cityA, City cityB);
	@Query("{ $or: [{ 'cityA' : ?0 }, { 'cityB' : ?0 }] }")
	List<Transfer> getAllWithCity(City city);
}
