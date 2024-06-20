package com.javainuse.bootmysqlcrud.entity.response;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {
    private Long id;

    private String title;
    private String description;
    private String category;
    private String price;
    private double discountPercentage;
    private double rating;
    private int stock;
    private String brand;
    private String sku;
    private String weight;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private String returnPolicy;
    private int minimumOrderQuantity;

    @ElementCollection
    private List<String> tags;

    @Embedded
    private Dimensions dimensions;

    @ElementCollection
    private List<Review> reviews;

    @Embedded
    private Meta meta;

    // Getters and Setters
    // Constructor
}

@Embeddable
@Getter
@Setter
class Dimensions {
    private double width;
    private double height;
    private double depth;

    // Getters and Setters
    // Constructor
}

@Embeddable
@Getter
@Setter
class Review {
    private int rating;
    private String comment;
    private String date;
    private String reviewerName;
    private String reviewerEmail;

    // Getters and Setters
    // Constructor
}

@Embeddable
@Getter
@Setter
class Meta {
    private String createdAt;
    private String updatedAt;
    private String barcode;
    private String qrCode;

}
