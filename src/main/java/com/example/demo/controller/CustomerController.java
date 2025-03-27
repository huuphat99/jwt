package com.example.demo.controller;

import com.example.demo.controller.base.BaseController;
import com.example.demo.handler.NotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerRequest;
import com.example.demo.model.ErrorModel;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseController {

  @Autowired
  private CustomerService customerService;

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerRequest request,
      Errors errors) {
    if (errors.hasErrors()) {
      createFailResponse(errors);
    }
    Customer customer = new Customer();
    BeanUtils.copyProperties(request, customer);
    Customer createdCustomer = customerService.createCustomer(customer);
    return new ResponseEntity<>(createdCustomer, HttpStatus.UNAUTHORIZED);
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<Customer>> getAllCustomers() {
    List<Customer> customers = customerService.getAllCustomers();
    return ResponseEntity.ok(customers);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or #id == principal.customerId")
  public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
    Customer customer = this.customerService.getCustomerById(id)
        .orElseThrow(() -> new NotFoundException(new ErrorModel("E15")));
    return ResponseEntity.ok(customer);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or #id == principal.customerId")
  public ResponseEntity<Customer> updateCustomer(
      @PathVariable Integer id,
      @Valid @RequestBody CustomerRequest request, Errors errors
  ) {
    if (errors.hasErrors()) {
      createFailResponse(errors);
    }
    Customer updatedCustomer = customerService.updateCustomer(id, request);
    return ResponseEntity.ok(updatedCustomer);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.noContent().build();
  }
}
