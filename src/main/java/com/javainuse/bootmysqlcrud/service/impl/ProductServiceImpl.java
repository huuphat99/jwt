package com.javainuse.bootmysqlcrud.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.entity.request.ProductRequest;
import com.javainuse.bootmysqlcrud.entity.response.PaginatedProductResponse;
import com.javainuse.bootmysqlcrud.entity.response.ProductResponse;
import com.javainuse.bootmysqlcrud.service.ProductService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public PaginatedProductResponse getAllProducts(Integer currentPage) {

        List<ProductResponse> productResponses = getListResponse();
        if (productResponses != null) {
            int totalSize = productResponses.size();
            int startItem = (currentPage - 1) * 10;
            int endItem = Math.min(startItem + 10, totalSize);

            if (startItem > totalSize) {
                return new PaginatedProductResponse(List.of(), 10, 0, totalSize);
            }
            if (currentPage == 0) {
                return new PaginatedProductResponse(List.of(), 10, 0, totalSize);
            }
            if (currentPage == 4) {
                startItem = startItem - 1;
                endItem = endItem - 4;
            }
            List<ProductResponse> paginatedProducts = productResponses.subList(startItem, endItem);
            for (ProductResponse productResponse : paginatedProducts) {
                if (productResponse.getId() == 13) {
                    productResponse.setPrice("test");
                }
                if (productResponse.getId() == 32) {
                    productResponse.setId(1L);
                }
                if (productResponse.getId() == 8) {
                    productResponse.setWeight("10kg");
                }
            }
            PaginatedProductResponse response = new PaginatedProductResponse();
            response.setProducts(paginatedProducts);
            response.setPageSize(10);
            response.setCurrentPage(currentPage);
            if (currentPage == 6) {
                response.setCurrentPage(5);
            }
            response.setTotalPage(totalSize % 10 == 0 ? totalSize / 10 : totalSize / 10 + 1);
            return response;
        }
        return null;
    }

    private List<ProductResponse> getListResponse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Sử dụng ClassPathResource để lấy tài nguyên từ classpath
            ClassPathResource resource = new ClassPathResource("products.json");
            InputStream inputStream = resource.getInputStream();
            return mapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
