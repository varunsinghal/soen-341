package com.soen.empower.repository;

import com.soen.empower.entity.Dislike;
import com.soen.empower.entity.Like;
import com.soen.empower.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     */
    User findById(Long id);

    /**
     * Find by username.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

    List<User> findByFullNameContainingIgnoreCase(String search);
}
