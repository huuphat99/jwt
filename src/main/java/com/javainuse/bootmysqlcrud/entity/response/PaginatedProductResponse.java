package com.javainuse.bootmysqlcrud.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedProductResponse {
    private List<ProductResponse> products;
    private int pageSize;
    private int currentPage;
    private int totalPage;
}
