package com.instagramapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.instagramapi.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByUsername(String username);
	
	
	@Query("SELECT u from User u where u.id IN:users")
	public List<User> findAllUsersByIds(@Param("users") List<Integer> userIds);
	
	@Query("SELECT DISTINCT u from User u Where u.username LIKE %:query% OR u.email LIKE %:query%")
	public List<User> findByQuery(@Param("query") String query);
}
