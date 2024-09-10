package com.ox.user.services;

import com.ox.user.converters.UserConverter;
import com.ox.user.dto.AuthenticationRequest;
import com.ox.user.dto.TokenDTO;
import com.ox.user.dto.UserDTO;
import com.ox.user.entities.User;
import com.ox.user.jwt.TokenProvider;
import com.ox.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider accessTokenProvider;

    private final TokenProvider refreshTokenProvider;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public TokenDTO register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.fromDTO(userDTO);
        userRepository.save(user);
        return createTokens(user);
    }

    public TokenDTO createTokens(User user) {
        String accessToken = accessTokenProvider.generateToken(user);
        String refreshToken = refreshTokenProvider.generateToken(user);
        return TokenDTO.builder()
                .accessToken(accessToken)
                .accessTokenExpiration(jwtService.extractExpiration(accessToken))
                .refreshToken(refreshToken)
                .refreshTokenExpiration(jwtService.extractExpiration(refreshToken))
                .build();
    }

    public TokenDTO authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail());
        return createTokens(user);
    }
}
