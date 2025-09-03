
// Intercepte les requêtes, lit le JWT et authentifie l’utilisateur s’il est valide.

package com.todo.backend.security;

import com.todo.backend.domain.AppUser;
import com.todo.backend.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component @RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtService jwt;
  private final AppUserRepository users;

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {

    String header = req.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      if (jwt.isValid(token)) {
        String email = jwt.getSubject(token);
        AppUser u = users.findByEmail(email).orElse(null);
        if (u != null) {
          var auth = new UsernamePasswordAuthenticationToken(
            email, null, List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole())));
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      }
    }
    chain.doFilter(req, res);
  }
}
