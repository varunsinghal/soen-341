package com.soen.empower.service;

import com.soen.empower.entity.User;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserService.
 */
@Service
public class UserService {
    
    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    /**
     * Find all.
     *
     * @return the list
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll())
            users.add(user);
        return users;
    }

    /**
     * Adds the.
     *
     * @param user the user
     */
    public void add(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * Find by name.
     *
     * @param username the username
     * @return the user
     */
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     */
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Save.
     *
     * @param user the user
     */
    public void save(User user) {
        userRepository.save(user);
    }
}
