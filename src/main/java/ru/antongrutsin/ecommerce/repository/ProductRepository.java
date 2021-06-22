package ru.antongrutsin.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.ecommerce.domain.Category;
import ru.antongrutsin.ecommerce.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    /*
    Методы для фильтров на странице
     */
    List<Product> findProductByCostGreaterThanEqualAndCostLessThanEqual(double cost, double cost2);
    List<Product> findProductByCategoryId(Category category);
    List<Product> findProductByCategoryIdAndCostGreaterThanEqualAndCostLessThanEqual(Category category, double cost, double cost2);
}