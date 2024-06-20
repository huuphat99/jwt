package com.javainuse.bootmysqlcrud.service;

import com.javainuse.bootmysqlcrud.entity.request.ProductRequest;
import com.javainuse.bootmysqlcrud.entity.response.PaginatedProductResponse;
import com.javainuse.bootmysqlcrud.entity.response.ProductDto;
import com.javainuse.bootmysqlcrud.entity.response.ProductResponse;
import com.javainuse.bootmysqlcrud.exception.NumberException;

import java.util.List;

public interface ProductService {

    PaginatedProductResponse getAllProducts(ProductRequest productRequest);
}
