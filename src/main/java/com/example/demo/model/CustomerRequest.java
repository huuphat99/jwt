package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

  @NotBlank(message = "E11")
  private String firstName;

  @NotBlank(message = "E12")
  private String lastName;

  @NotBlank(message = "E07")
  @Column(unique = true)
  private String email;

  @NotBlank(message = "E13")
  private String phoneNumber;
}
