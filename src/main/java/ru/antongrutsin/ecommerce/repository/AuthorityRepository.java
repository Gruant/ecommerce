package ru.antongrutsin.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.ecommerce.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String>{
}