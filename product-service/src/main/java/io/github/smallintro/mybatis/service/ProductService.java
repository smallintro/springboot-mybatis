package io.github.smallintro.mybatis.service;

import io.github.smallintro.mybatis.model.Product;

import java.util.List;

public interface ProductService {
    int addProduct(Product product);

    int updateProduct(Integer productId, Product product);

    int removeProducts(List<Integer> productIds);

    Product getProductById(Integer productId);

    List<Product> getProductByName(String productName);
}
