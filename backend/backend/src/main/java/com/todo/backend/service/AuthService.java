
// Gère la logique de login/register et génère le JWT.

package com.todo.backend.service;

import com.todo.backend.domain.AppUser;
import com.todo.backend.dto.auth.*;
import com.todo.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service @RequiredArgsConstructor
public class AuthService {
  private final UserService users;
  private final PasswordEncoder encoder;
  private final JwtService jwt;

  public AuthResponse register(RegisterRequest req) {
    if (users.exists(req.email())) throw new IllegalArgumentException("Email already used");
    AppUser u = users.createUser(req.email(), req.password(), "USER");
    String token = jwt.generate(u.getEmail(), Map.of("role", u.getRole()));
    return new AuthResponse(token);
  }

  public AuthResponse login(LoginRequest req) {
    AppUser u = users.findByEmail(req.email())
      .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    if (!encoder.matches(req.password(), u.getPasswordHash()))
      throw new IllegalArgumentException("Invalid credentials");
    String token = jwt.generate(u.getEmail(), Map.of("role", u.getRole()));
    return new AuthResponse(token);
  }
}

