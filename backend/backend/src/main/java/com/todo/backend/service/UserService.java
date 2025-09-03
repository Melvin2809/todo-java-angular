
// Gère la création et recherche des utilisateurs.

package com.todo.backend.service;

import com.todo.backend.domain.AppUser;
import com.todo.backend.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class UserService {
  private final AppUserRepository repo;
  private final PasswordEncoder encoder;

  public AppUser createUser(String email, String rawPwd, String role) {
    var u = AppUser.builder()
      .email(email)
      .passwordHash(encoder.encode(rawPwd))
      .role(role == null ? "USER" : role)
      .build();
    return repo.save(u);
  }

  public Optional<AppUser> findByEmail(String email) { return repo.findByEmail(email); }
  public boolean exists(String email) { return repo.existsByEmail(email); }
}
