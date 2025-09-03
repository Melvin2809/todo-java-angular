
// expose 2 endpoints publics :

//   POST /api/auth/register → crée un utilisateur + renvoie un token

//   POST /api/auth/login → vérifie credentials + renvoie un token

package com.todo.backend.controller;

import com.todo.backend.dto.auth.*;
import com.todo.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService auth;

  @PostMapping("/register")
  public AuthResponse register(@Valid @RequestBody RegisterRequest req) {
    return auth.register(req);
  }

  @PostMapping("/login")
  public AuthResponse login(@Valid @RequestBody LoginRequest req) {
    return auth.login(req);
  }
}
