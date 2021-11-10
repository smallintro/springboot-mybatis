package io.github.smallintro.mybatis;

import io.github.smallintro.mybatis.mapper.ProductMapper;
import io.github.smallintro.mybatis.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    ProductMapper productMapper;

    @Test
    public void whenRecordsInDatabase_shouldReturnArticleWithGivenId() {
        Product product = productMapper.getProductById(101);

        assertThat(product).isNotNull();
        assertThat(product.getProductId()).isEqualTo(101);
        assertThat(product.getProductName()).isEqualTo("demo device");
        assertThat(product.getProductPrice()).isEqualTo(99.99);
        assertThat(product.getProductCount()).isEqualTo(10);
    }

}
