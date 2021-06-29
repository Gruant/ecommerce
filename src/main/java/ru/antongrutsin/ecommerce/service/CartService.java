package ru.antongrutsin.ecommerce.service;

import ru.antongrutsin.ecommerce.domain.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {
    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    BigDecimal getTotal();
}
