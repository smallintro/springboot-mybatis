package io.github.smallintro.mybatis.mapper;


import io.github.smallintro.mybatis.model.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO t_product (product_id, product_name, product_price, product_count) values (#{product.productId}, #{product.productName}, #{product.productPrice}, #{product.productCount})")
    int addProduct(@Param("product") Product product);

    @Select("SELECT product_id as productId, product_name as productName, product_price as productPrice, product_count as productCount FROM t_product WHERE product_id = #{productId}")
    Product getProductById(@Param("productId") Integer productId);

    int updateProduct(@Param("productId") Integer productId, @Param("product") Product product);

    int removeProducts(@Param("productIds") List<Integer> productIds);

    int getProductCountByName(@Param("productName") String productName);

    List<Product> getProductByName(@Param("productName") String productName);
}
