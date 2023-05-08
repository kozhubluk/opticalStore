package com.example.opticalStore.services;

import com.example.opticalStore.models.Role;
import com.example.opticalStore.models.User;
import com.example.opticalStore.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("user1", true, Collections.singleton(Role.USER)));
        userList.add(new User("user2", true, Collections.singleton(Role.USER)));

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.list();

        assertEquals(userList, result);
    }

    @Test
    public void testGetUserByPrincipal() {
        User user = new User("user1", "password", true, Collections.singleton(Role.USER));
        Mockito.when(userRepository.findByUsername("user1")).thenReturn(user);

        Principal principal = new UsernamePasswordAuthenticationToken("user1", "password");
        User result = userService.getUserByPrincipal(principal);

        assertEquals(user, result);
    }
}