
// Données reçues pour créer un compte (email + mot de passe).

package com.todo.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@Email @NotBlank String email,
                              @NotBlank String password) {}
