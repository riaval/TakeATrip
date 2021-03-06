package com.takeatrip.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.takeatrip.domain.City;

@Repository
public interface CityRepository extends CrudRepository<City, String> {
    @Query("{ '$id' : ?0 }")
    City findById(String id);

    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<City> searchFirstLetteres(String beginning);
    
    @Query("{'country':?0}")
    List<City> getCountry(String country);
}
