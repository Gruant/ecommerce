package ru.antongrutsin.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.ecommerce.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}