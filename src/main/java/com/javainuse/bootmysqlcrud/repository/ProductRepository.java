package com.javainuse.bootmysqlcrud.repository;

import com.javainuse.bootmysqlcrud.entity.Product;
import com.javainuse.bootmysqlcrud.entity.User;
import com.javainuse.bootmysqlcrud.entity.request.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    @Query("""
            select u from Product u where u.productName = :#{#request.productName}
            and u.description = :#{#request.description}
            and u.price = :#{#request.price}
            and u.deleteFlg = false
            """)
    List<Product> getListProductByCondition(@Param("request") ProductRequest request);
}
