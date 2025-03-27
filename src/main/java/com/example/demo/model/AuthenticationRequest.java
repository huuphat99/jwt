package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

  @NotBlank(message = "E03")
  private String username;
  @NotBlank(message = "E04")
  private String password;
}
