package io.github.smallintro.mybatis.controller;

import io.github.smallintro.mybatis.model.Product;
import io.github.smallintro.mybatis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/save")
    public ResponseEntity addProduct(@NonNull @RequestBody Product product) {
        int addCount = productService.addProduct(product);
        return new ResponseEntity(String.format("%d product added with productId: %d", addCount, product.getProductId()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") Integer productId, @NonNull @RequestBody Product product) {
        int updateCount = productService.updateProduct(productId, product);
        return new ResponseEntity(String.format("%d product updated with productId: %s", updateCount, productId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity removeProduct(@RequestBody List<Integer> productIds) {
        int delCount = productService.removeProducts(productIds);
        return new ResponseEntity(String.format("%d product deleted with productId: %s", delCount, productIds), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity getProductById(@PathVariable("id") Integer productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @PostMapping(value = "/get/{name}")
    public ResponseEntity getProductByName(@PathVariable("name") String productName) {
        List<Product> products = productService.getProductByName(productName);
        return new ResponseEntity(products, HttpStatus.OK);
    }
}
