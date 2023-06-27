package com.Tracker.authenticationservice.repository;

import com.Tracker.authenticationservice.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>
{
    User findByUsername(String username);
}