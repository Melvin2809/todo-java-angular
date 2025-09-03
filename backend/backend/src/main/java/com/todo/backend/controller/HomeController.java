// src/main/java/com/todo/backend/controller/HomeController.java
package com.todo.backend.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HomeController {
  @GetMapping("/")
  public Map<String,String> home() {
    return Map.of("status","ok","api","/swagger-ui/index.html");
  }
}
