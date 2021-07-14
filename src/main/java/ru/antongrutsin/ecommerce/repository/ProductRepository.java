package ru.antongrutsin.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.ecommerce.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getByCategoryId(Long id);
}