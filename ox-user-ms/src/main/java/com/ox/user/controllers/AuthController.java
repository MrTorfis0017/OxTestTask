package com.ox.user.controllers;

import com.ox.user.dto.TokenDTO;
import com.ox.user.dto.UserDTO;
import com.ox.user.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/public")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userDTO));
    }

    @PostMapping("/login")
    public void authenticate(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password
    ) {

        System.out.println(username);
        System.out.println(password);
    }
}
