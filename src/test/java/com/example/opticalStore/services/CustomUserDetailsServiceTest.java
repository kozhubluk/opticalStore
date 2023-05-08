package com.example.opticalStore.services;

import com.example.opticalStore.models.Role;
import com.example.opticalStore.repositories.UserRepository;
import com.example.opticalStore.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    public void loadUserByUsername_shouldReturnUserDetails() {
        // Arrange
        String username = "testUser";
        User user = new User(username, "password", true, Collections.singleton(Role.USER));
        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(Role.USER));
    }

    @Test
    public void loadUserByUsername_shouldThrowUsernameNotFoundException() {
        String username = "testUser";
        when(userRepository.findByUsername(username)).thenReturn(null);

        userDetailsService.loadUserByUsername(username);

    }
}