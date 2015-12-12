package com.takeatrip.repository;

import com.takeatrip.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Query("{ 'email' : ?0 }")
    User findByEmail(String email);

}
