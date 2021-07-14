package ru.antongrutsin.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.ecommerce.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
