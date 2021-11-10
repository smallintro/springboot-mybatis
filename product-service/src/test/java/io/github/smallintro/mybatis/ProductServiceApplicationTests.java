package io.github.smallintro.mybatis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.smallintro.mybatis.model.Product;
import io.github.smallintro.mybatis.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class ProductServiceApplicationTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ProductService productService;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    void contextLoads() {

    }

    public static Product buildProduct(Integer Id) {
        Product product = Product.builder()
                .productId(Id)
                .productName("Test" + Id)
                .productPrice(.99 + Id)
                .productCount(Id)
                .build();
        System.out.println(product);
        return product;
    }
}
