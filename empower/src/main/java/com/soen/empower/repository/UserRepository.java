package com.soen.empower.repository;

import org.springframework.data.repository.CrudRepository;

import com.soen.empower.entity.User;

/**
 * The Interface UserRepository.
 */
public interface UserRepository  extends CrudRepository<User, String>{
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User findById(Long id);
	
	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	public User findByUsername(String username);
}
