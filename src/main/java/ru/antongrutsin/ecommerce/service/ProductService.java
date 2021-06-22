package ru.antongrutsin.ecommerce.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(long id) throws NotFoundException {
        Optional<Product> element = productRepository.findById(id);
        return element.orElseThrow(() -> new NotFoundException("Entity not found or id is wrong"));
    }

    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
