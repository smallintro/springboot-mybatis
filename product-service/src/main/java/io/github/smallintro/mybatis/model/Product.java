package io.github.smallintro.mybatis.model;

import lombok.Data;

@Data
public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private int productCount;
}
