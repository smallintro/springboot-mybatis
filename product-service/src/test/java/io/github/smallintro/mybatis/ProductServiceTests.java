package io.github.smallintro.mybatis;

import io.github.smallintro.mybatis.mapper.ProductMapper;
import io.github.smallintro.mybatis.model.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTests extends ProductServiceApplicationTests {

    @MockBean
    private ProductMapper productMapper;

    @Test
    public void testService_addProduct() {
        // Data Already Exists
        Mockito.when(productMapper.getProductById(100)).thenReturn(buildProduct(100));
        int count = productService.addProduct(buildProduct(100));
        assertThat(count).isEqualTo(0); // product with id 100 is already added
        // Data Not Exists
        Mockito.when(productMapper.getProductById(101)).thenReturn(null);
        Mockito.when(productMapper.addProduct(buildProduct(101))).thenReturn(1);
        count = productService.addProduct(buildProduct(101));
        assertThat(count).isEqualTo(1); // 1 product is added with id 101
    }

    @Test
    public void testService_updateProduct() {
        Mockito.when(productMapper.updateProduct(102, buildProduct(103))).thenReturn(0);
        // No data found
        int count = productService.updateProduct(102, buildProduct(103)); // updating product with id 102 with 103
        assertThat(count).isEqualTo(0); // 0 product updated as product with 102 does not exist
        // Data found
        Mockito.when(productMapper.updateProduct(102, buildProduct(103))).thenReturn(1);
        count = productService.updateProduct(102, buildProduct(103)); // updating product with id 102 with 103
        assertThat(count).isEqualTo(1); // 1 product updated as product with 102 exist
    }

    @Test
    public void testService_removeProducts() {
        Mockito.when(productMapper.removeProducts(Arrays.asList(new Integer[]{104}))).thenReturn(0);
        // No data found
        int count = productService.removeProducts(Arrays.asList(new Integer[]{104}));
        assertThat(count).isEqualTo(0); // 0 product removed as product with id 104 does not exist
        // Data found
        Mockito.when(productMapper.removeProducts(Arrays.asList(new Integer[]{104}))).thenReturn(1);
        count = productService.removeProducts(Arrays.asList(new Integer[]{104}));
        assertThat(count).isEqualTo(1); // 1 product removed as product with 104 exist
    }

    @Test
    public void testService_getProductById() {
        Mockito.when(productMapper.getProductById(105)).thenReturn(null);
        // No data found
        Product product = productService.getProductById(105);
        assertThat(product).isNull(); // product with id 105 does not exist
        // Data found
        Mockito.when(productMapper.getProductById(105)).thenReturn(buildProduct(105));
        product = productService.getProductById(105);
        assertThat(product).isNotNull();
        assertThat(product.getProductId()).isEqualTo(105);
        assertThat(product.getProductName()).isEqualTo("Test105");
        assertThat(product.getProductPrice()).isEqualTo(105.99);
        assertThat(product.getProductCount()).isEqualTo(105);
    }

    @Test
    public void testService_getProductByName() {
        Mockito.when(productMapper.getProductByName("Test106")).thenReturn(null);
        // No data found
        List<Product> products = productService.getProductByName("Test106");
        assertThat(products).isNull(); // product with id 105 does not exist
        // Data found
        products = Arrays.asList(new Product[]{buildProduct(106)});
        Mockito.when(productMapper.getProductByName("Test106")).thenReturn(products);
        products = productService.getProductByName("Test106");
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(1);
        Product product = products.get(0);
        assertThat(product).isNotNull();
        assertThat(product.getProductId()).isEqualTo(106);
        assertThat(product.getProductName()).isEqualTo("Test106");
        assertThat(product.getProductPrice()).isEqualTo(106.99);
        assertThat(product.getProductCount()).isEqualTo(106);
    }

}
