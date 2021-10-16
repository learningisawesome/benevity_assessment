package com.benevity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.benevity.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.familyName like %:familyName%")
	public User findUserByFamilyName(String familyName);
	
}
