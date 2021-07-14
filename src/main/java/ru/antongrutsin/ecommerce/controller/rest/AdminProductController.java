package ru.antongrutsin.ecommerce.controller.rest;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.service.api.ProductApiService;

import java.io.IOException;


@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductApiService productApiService;

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public Product getById(@PathVariable Long id){
        return productApiService.getById(id);
    }

    @DeleteMapping("/remove/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productApiService.deleteById(id);
    }

    @DeleteMapping("/remove/product")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByObject(@RequestBody Product product){
        productApiService.deleteByObject(product);
    }

    @PostMapping("/add")
    @Transactional
    public void addProduct(@RequestBody Product product, @RequestBody MultipartFile file) throws IOException {
        productApiService.addProduct(product, file);
    }

    @PatchMapping("/update/{id}/{cost}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id, @PathVariable Double cost) throws NotFoundException {
        return productApiService.updateProductCost(product, id, cost);

    }
}
