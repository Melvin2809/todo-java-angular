
// Représente un utilisateur en base (email, mot de passe hashé, rôle, date de création).

package com.todo.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name="app_user")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AppUser {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, unique=true)
  private String email;

  @Column(name="password_hash", nullable=false)
  private String passwordHash;

  @Column(nullable=false)
  private String role = "USER";

  @Column(name="created_at", nullable=false)
  private Instant createdAt = Instant.now();
}
