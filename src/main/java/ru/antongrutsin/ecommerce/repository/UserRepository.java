package ru.antongrutsin.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.ecommerce.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLogin(String username);
}
