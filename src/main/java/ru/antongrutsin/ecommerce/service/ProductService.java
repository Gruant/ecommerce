package ru.antongrutsin.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.domain.ProductImage;
import ru.antongrutsin.ecommerce.repository.ProductImageRepository;
import ru.antongrutsin.ecommerce.repository.ProductRepository;
import ru.antongrutsin.ecommerce.service.exception.NotFoundException;
import ru.antongrutsin.ecommerce.wrapper.ProductWrapper;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final Path root = Paths.get("static/img/");

//    @PostConstruct
//    public void checkRootDir() throws IOException {
//        if (Files.notExists(root)){
//            Files.createDirectory(root);
//        }
//    }

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final CategoryService categoryService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) throws NotFoundException {
        Optional<Product> element = productRepository.findById(id);
        return element.orElseThrow(() -> new NotFoundException("Entity not found or id is wrong"));
    }

    public List<Product> getByCategoryId(Long id){
        return productRepository.findProductByCategoryId(categoryService.findById(id));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void addProduct(ProductWrapper product) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(product.getImage().getOriginalFilename()));
        String fileLocation = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator
                + "static" + File.separator + "img").getAbsolutePath() + File.separator + fileName;

        FileOutputStream output = new FileOutputStream(fileLocation);
        output.write(product.getImage().getBytes());
        output.close();


        Product productDomain = new Product();
        productDomain.setName(product.getName());
        productDomain.setDescription(product.getDescription());
        productDomain.setCost(product.getCost());
        productDomain.setCategoryId(categoryService.findByName(product.getCategory()));
        Product newProduct = productRepository.save(productDomain);

        ProductImage productImage = new ProductImage();
        productImage.setProduct(newProduct);
        productImage.setPath("img" + File.separator + fileName);

        productImageRepository.save(productImage);
    }

    public Product updateProductCost(Long id, Double cost) throws ru.antongrutsin.ecommerce.service.exception.NotFoundException {
        Optional<Product> element = productRepository.findById(id);
        Product updatedProduct = element.orElseThrow(() -> new NotFoundException("Entity not found or id is wrong"));
        updatedProduct.setCost(cost);
        return productRepository.save(updatedProduct);
    }

}
