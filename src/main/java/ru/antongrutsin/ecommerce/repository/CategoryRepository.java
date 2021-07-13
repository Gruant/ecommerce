package ru.antongrutsin.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.antongrutsin.ecommerce.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}