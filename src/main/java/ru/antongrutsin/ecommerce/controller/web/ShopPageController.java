package ru.antongrutsin.ecommerce.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antongrutsin.ecommerce.domain.Category;
import ru.antongrutsin.ecommerce.domain.Product;
import ru.antongrutsin.ecommerce.service.CategoryService;
import ru.antongrutsin.ecommerce.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/shopPage")
@RequiredArgsConstructor
public class ShopPageController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public String showAllProducts(Model model){
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", getCategories());
        return "shop";
    }

    @GetMapping
    public String showProductsByCategoryId(Model model,
                                           @RequestParam Long categoryId){
        List<Product> products = productService.getByCategoryId(categoryId);
        model.addAttribute("products", products);
        model.addAttribute("categories", getCategories());
        return "shop";
    }

    private List<Category> getCategories(){
        return categoryService.getAll();
    }
}
