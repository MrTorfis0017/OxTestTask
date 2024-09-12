package com.ox.user.services;

import com.ox.user.entities.User;
import com.ox.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void loadUserByUsernameTest() {
        User user = new User();
        user.setUsername("User");
        user.setPassword("Password");
        user.setRole("USER");

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        UserDetails result = userService.loadUserByUsername("User");

        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getPassword(), user.getPassword());
        assertEquals(result.getPassword(), user.getPassword());
    }

    @Test
    public void loadUserByUsernameUsernameNotFoundExceptionTest() {
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("User"));
    }

}
