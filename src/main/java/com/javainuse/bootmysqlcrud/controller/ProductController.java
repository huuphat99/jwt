package com.javainuse.bootmysqlcrud.controller;

import com.javainuse.bootmysqlcrud.entity.request.ProductRequest;
import com.javainuse.bootmysqlcrud.entity.response.PaginatedProductResponse;
import com.javainuse.bootmysqlcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<?> getAllProductsByCondition(@RequestParam Integer currentPage) {
        PaginatedProductResponse response = productService.getAllProducts(currentPage);
        if (response.getCurrentPage() == 5) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (response.getCurrentPage() == 3) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
