
// Fournit l’accès aux utilisateurs en base (chercher par email, vérifier si un email existe).

package com.todo.backend.repository;

import com.todo.backend.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByEmail(String email);
  boolean existsByEmail(String email);
}
