package com.javainuse.bootmysqlcrud.entity.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private Integer pageSize = 10;
    private Integer currentPage = 1;
}
