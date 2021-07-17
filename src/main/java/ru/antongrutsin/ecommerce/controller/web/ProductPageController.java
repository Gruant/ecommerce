package ru.antongrutsin.ecommerce.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/productPage")
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductService productService;

    @GetMapping
    public String showProduct(Model model, @RequestParam Long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product";

    }

}
