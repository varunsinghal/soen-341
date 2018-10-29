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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ParentRepository parentRepository;

    public UserService(UserRepository userRepository, TeacherRepository teacherRepository, ParentRepository parentRepository) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.parentRepository = parentRepository;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        for (User user : userRepository.findAll())
            users.add(user);
        return users;
    }

    public void add(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole().equals("ROLE_TEACHER")) user.setTeacher(teacherRepository.save(new Teacher()));
        if (user.getRole().equals("ROLE_PARENT")) user.setParent(parentRepository.save(new Parent()));
        userRepository.save(user);
    }

    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findByPartialName(String search) {
        return userRepository.findByFullNameContainingIgnoreCase(search);
    }
}
