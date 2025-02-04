package com.health.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.health.entity.User;



public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);
	

}
