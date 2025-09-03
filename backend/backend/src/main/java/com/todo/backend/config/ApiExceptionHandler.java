package com.todo.backend.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
    var msg = ex.getBindingResult().getFieldErrors().stream()
        .findFirst().map(e -> e.getField()+" "+e.getDefaultMessage())
        .orElse("Validation error");
    return ResponseEntity.badRequest().body(Map.of(
        "timestamp", Instant.now().toString(),
        "status", 400,
        "error", "Bad Request",
        "message", msg
    ));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> handleConstraint(DataIntegrityViolationException ex) {
    return ResponseEntity.badRequest().body(Map.of(
        "timestamp", Instant.now().toString(),
        "status", 400,
        "error", "Bad Request",
        "message", "Constraint violation: "+ ex.getMostSpecificCause().getMessage()
    ));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegal(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(Map.of(
        "timestamp", Instant.now().toString(),
        "status", 400,
        "error", "Bad Request",
        "message", ex.getMessage()
    ));
  }
}
