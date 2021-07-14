package ru.antongrutsin.ecommerce.controller.rest;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.domain.ProductImage;
import ru.antongrutsin.ecommerce.service.CategoryService;
import ru.antongrutsin.ecommerce.service.api.ProductApiService;
import ru.antongrutsin.ecommerce.wrapper.ProductWrapper;

import java.io.IOException;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductApiService productApiService;
    private final CategoryService categoryService;

    @GetMapping(value = "/{id}", produces = "application/json")
    @Transactional(readOnly = true)
    public Product getById(@PathVariable Long id) {
        return productApiService.getById(id);
    }

    @DeleteMapping("/remove")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id){
        productApiService.deleteById(id);
    }


    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    @Transactional
    public void addProduct(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "cost") Double cost,
            @RequestParam(value = "categoryId") String category,
            @RequestParam(value = "file") MultipartFile multipartFile
            ) throws IOException {
        ProductWrapper product = new ProductWrapper();
        product.setName(name);
        product.setDescription(description);
        product.setCost(cost);
        product.setCategory(category);
        product.setImage(multipartFile);
        productApiService.addProduct(product);
    }

    @PatchMapping("/update")
    @Transactional
    public Product updateProduct(@RequestParam Long id, @RequestParam Double cost){
        return productApiService.updateProductCost(id, cost);

    }
}
