package io.github.smallintro.mybatis.util;

import io.github.smallintro.mybatis.model.Product;

public class ProductValidatorUtils {

    public static boolean isProductIdValid(Integer produceId) {
        if (null == produceId || produceId <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isProductValid(Product product) {
        if (null == product) {
            return false;
        }
        if (product.getProductId() <= 0) {
            return false;
        }
        if (null == product.getProductName() || product.getProductName().length() > 30) {
            return false;
        }

        if (product.getProductPrice() <= 0) {
            return false;
        }

        if (product.getProductCount() < 0) {
            return false;
        }
        return true;
    }

}
