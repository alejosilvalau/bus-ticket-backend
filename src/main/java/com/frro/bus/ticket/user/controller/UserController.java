package com.frro.bus.ticket.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/users")
  public String getHello() {
    return "Hello, World!";
  }
}
