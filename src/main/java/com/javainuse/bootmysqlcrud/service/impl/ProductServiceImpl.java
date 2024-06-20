package com.javainuse.bootmysqlcrud.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javainuse.bootmysqlcrud.entity.request.ProductRequest;
import com.javainuse.bootmysqlcrud.entity.response.PaginatedProductResponse;
import com.javainuse.bootmysqlcrud.entity.response.ProductResponse;
import com.javainuse.bootmysqlcrud.exception.NumberException;
import com.javainuse.bootmysqlcrud.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public PaginatedProductResponse getAllProducts(ProductRequest productRequest) throws NumberException {
        if (productRequest.getCurrentPage() == null || productRequest.getPageSize() == null) {
            throw new NumberException("Vui lòng nhập giá trị currentPage và pageSize.");
        }

        List<ProductResponse> productResponses = getListResponse();
        if (productResponses != null) {
            int totalSize = productResponses.size();
            int startItem = (productRequest.getCurrentPage() - 1) * productRequest.getPageSize();
            int endItem = Math.min(startItem + productRequest.getPageSize(), totalSize);

            if (startItem > totalSize) {
                return new PaginatedProductResponse(List.of(), productRequest.getPageSize(), 0, totalSize);
            }
            if (productRequest.getCurrentPage() == 4) {
                startItem = startItem - 1;
                endItem = endItem - 4;
            }
            List<ProductResponse> paginatedProducts = productResponses.subList(startItem, endItem);
            for (ProductResponse productResponse : paginatedProducts) {
                if (productResponse.getId() == 13) {
                    productResponse.setPrice("test");
                }
                if (productResponse.getId() == 32) {
                    productResponse.setId(null);
                }
                if (productResponse.getId() == 8) {
                    productResponse.setWeight("10kg");
                }
            }
            PaginatedProductResponse response = new PaginatedProductResponse();
            response.setProducts(Collections.singletonList(paginatedProducts));
            response.setPageSize(productRequest.getPageSize());
            response.setCurrentPage(productRequest.getCurrentPage());
            if (productRequest.getCurrentPage() == 6) {
                response.setCurrentPage(5);
            }
            response.setTotalPage(totalSize % productRequest.getPageSize() == 0 ? totalSize / productRequest.getPageSize() : totalSize / productRequest.getPageSize() + 1);
            return response;
        }
        return null;
    }

    private List<ProductResponse> getListResponse() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(ResourceUtils.getFile("classpath:products.json").getAbsolutePath()), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
