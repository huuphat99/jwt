package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

  @NotBlank(message = "E03")
  @Size(min = 4, max = 50, message = "E05")
  @Column(unique = true)
  private String username;

  @NotBlank(message = "E04")
  @Size(min = 5, message = "E06")
  private String password;

  @NotBlank(message = "E07")
  @Column(unique = true)
  private String email;

  @NotBlank(message = "E08")
  private String role;
}
