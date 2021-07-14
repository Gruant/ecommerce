package ru.antongrutsin.ecommerce.service.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.domain.ProductImage;
import ru.antongrutsin.ecommerce.repository.ProductImageRepository;
import ru.antongrutsin.ecommerce.repository.ProductRepository;
import ru.antongrutsin.ecommerce.service.CategoryService;
import ru.antongrutsin.ecommerce.service.exception.NotFoundException;
import ru.antongrutsin.ecommerce.wrapper.ProductWrapper;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductApiService {

    private final Path root = Paths.get("images/");

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryService categoryService;


    public Product getById(Long id) throws NotFoundException {

        Optional<Product> element = productRepository.findById(id);
        return element.orElseThrow(() -> new NotFoundException("Entity not found or id is wrong"));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteByObject(Product product) {
        productRepository.delete(product);
    }


    public void addProduct(ProductWrapper product) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(product.getImage().getOriginalFilename()));
        Path filePath = root.resolve(fileName);
        FileOutputStream output = new FileOutputStream(String.valueOf(filePath));
        output.write(product.getImage().getBytes());


        Product productDomain = new Product();
        productDomain.setName(product.getName());
        productDomain.setDescription(product.getDescription());
        productDomain.setCost(product.getCost());
        productDomain.setCategoryId(categoryService.findByName(product.getCategory()));
        Product newProduct = productRepository.save(productDomain);

        ProductImage productImage = new ProductImage();
        productImage.setProduct(newProduct);
        productImage.setPath(filePath.toString());

        productImageRepository.save(productImage);
    }

    public Product updateProductCost(Long id, Double cost) throws NotFoundException {
        Optional<Product> element = productRepository.findById(id);
        Product updatedProduct = element.orElseThrow(() -> new NotFoundException("Entity not found or id is wrong"));
        updatedProduct.setCost(cost);
        return productRepository.save(updatedProduct);
    }
}
