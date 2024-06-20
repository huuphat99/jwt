package com.javainuse.bootmysqlcrud.entity.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String productName;
    private String description;
    private String price;
    private Boolean deleteFlg;
}
