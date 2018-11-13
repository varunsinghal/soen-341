package com.soen.empower.service;

import com.soen.empower.entity.Parent;
import com.soen.empower.entity.Teacher;
import com.soen.empower.entity.User;
import com.soen.empower.repository.ParentRepository;
import com.soen.empower.repository.TeacherRepository;
import com.soen.empower.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User as a service.
 *
 * @version 4.0
 * @since 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ParentRepository parentRepository;

    /**
     * Dependency injection to validate the test cases.
     *
     * @param userRepository    user table connection
     * @param teacherRepository teacher table connection
     * @param parentRepository  parent table connection
     */
    public UserService(UserRepository userRepository, TeacherRepository teacherRepository, ParentRepository parentRepository) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.parentRepository = parentRepository;
    }

    /**
     * Find all users in the system.
     *
     * @return list of users
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll())
            users.add(user);
        return users;
    }

    /**
     * Adds the model attribute user to the db.
     *
     * @param user entity
     */
    public void add(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole().equals("ROLE_TEACHER")) user.setTeacher(teacherRepository.save(new Teacher()));
        if (user.getRole().equals("ROLE_PARENT")) user.setParent(parentRepository.save(new Parent()));
        userRepository.save(user);
    }

    /**
     * locate user by its username
     *
     * @param username user's username
     * @return user object
     */
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * find by user id.
     *
     * @param id user id
     * @return entity
     */
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * create a new user or update the existing user, taken care by the user id.
     *
     * @param user entity
     */
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * find users by searching on full name
     * @param search string
     * @return list of users
     */
    public List<User> findByPartialName(String search) {
        return userRepository.findByFullNameContainingIgnoreCase(search);
    }
}
