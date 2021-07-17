package ru.antongrutsin.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antongrutsin.ecommerce.domain.Category;
import ru.antongrutsin.ecommerce.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    private void setCategoryRepository(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) { return categoryRepository.findById(id).get();}

    public Category findByName(String category) { return categoryRepository.findByName(category); }

}