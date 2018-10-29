package com.soen.empower.service;

import com.soen.empower.repository.ParentRepository;
import com.soen.empower.repository.TeacherRepository;
import com.soen.empower.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private ParentRepository parentRepository;


    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository, teacherRepository, parentRepository);
    }

    @Test
    public void findAll() {

    }

    @Test
    public void findByName() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByPartialName() {
    }
}