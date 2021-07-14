package ru.antongrutsin.ecommerce.service.api;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.domain.ProductImage;
import ru.antongrutsin.ecommerce.repository.ProductImageRepository;
import ru.antongrutsin.ecommerce.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApiService {

    private final Path root = Paths.get("/static/img/product");

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;


    public Product getById(Long id){
        return productRepository.getById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteByObject(Product product) {
        productRepository.delete(product);
    }

    @Transactional
    public void addProduct(Product product, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path filePath = this.root.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        Product newProduct = productRepository.save(product);
        productImageRepository.save(new ProductImage(newProduct.getId(), filePath.toString()));
    }

    public Product updateProductCost(Product product, Long id, Double cost) throws NotFoundException {
        Optional<Product> element = productRepository.findById(id);
        Product updatedProduct = element.orElseThrow(() -> new NotFoundException("Entity not found or id is wrong"));
        updatedProduct.setCost(cost);
        return productRepository.save(updatedProduct);
    }
}
