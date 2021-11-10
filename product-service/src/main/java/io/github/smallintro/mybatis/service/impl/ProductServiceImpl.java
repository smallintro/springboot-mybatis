package io.github.smallintro.mybatis.service.impl;

import io.github.smallintro.mybatis.mapper.ProductMapper;
import io.github.smallintro.mybatis.model.Product;
import io.github.smallintro.mybatis.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper mapper;

    @Override
    public int addProduct(Product product) {
        Product productExists = mapper.getProductById(product.getProductId());
        int count = 0;
        if (null == productExists) {
            count = mapper.addProduct(product);
        }
        log.info("Added {} product with Id: {}, name: {}", count, product.getProductId(), product.getProductName());
        return count;
    }

    @Override
    public int updateProduct(Integer productId, Product product) {
        int count = mapper.updateProduct(productId, product);
        log.info("Updated {} product with Id: {}, name: {}", count, productId, product.getProductName());
        return count;
    }

    @Override
    public int removeProducts(List<Integer> productIds) {
        int count = mapper.removeProducts(productIds);
        log.info("Deleted {} product(s) with Id:", count, productIds);
        return count;
    }

    @Override
    public Product getProductById(Integer productId) {
        Product product = mapper.getProductById(productId);
        log.info("Queried product with Id: {}", productId);
        return product;
    }

    @Override
    public List<Product> getProductByName(String productName) {
        List<Product> products = mapper.getProductByName(productName);
        int count = mapper.getProductCountByName(productName);
        log.info("Queried {} product(s) with name: {}", count, productName);
        return products;
    }

}
