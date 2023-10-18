package org.project.backapi.service;

import lombok.RequiredArgsConstructor;
import org.project.backapi.domain.User;
import org.project.backapi.domain.UserRole;
import org.project.backapi.dto.AuthRequest;
import org.project.backapi.dto.AuthResponse;
import org.project.backapi.dto.RegisterRequest;
import org.project.backapi.dto.UserInfoResponse;
import org.project.backapi.exception.UserAuthenticationException;
import org.project.backapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        try {
            var user = User.builder()
                    .email(request.getEmail())
                    .firstname(request.getFirstname())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .lastname(request.getLastname())
                    .userRole(UserRole.SUBSCRIBER)
                    .build();
            if (userRepository.existsByEmail(request.getEmail()))
                throw new UserAuthenticationException("User already exists!");
            userRepository.save(user);
            var token = jwtService.generateToken(user);
            return AuthResponse.builder()
                    .str(token)
                    .build();
        } catch (UserAuthenticationException e) {
            return AuthResponse.builder()
                    .str(e.getMessage())
                    .build();
        }
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .str(token)
                .build();
    }

    public UserInfoResponse getUserInfo(String token) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow();
        return UserInfoResponse.builder()
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .id(user.getId())
                .build();
    }
}
