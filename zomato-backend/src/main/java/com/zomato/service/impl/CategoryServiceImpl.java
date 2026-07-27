package com.zomato.service.impl;

import com.zomato.dto.CategoryRequest;
import com.zomato.dto.CategoryResponse;
import com.zomato.entity.Category;
import com.zomato.repository.CategoryRepository;
import com.zomato.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse addCategory(CategoryRequest request) {

        Category category = Category.builder()
                .categoryName(request.getCategoryName())
                .description(request.getDescription())
                .active(true)
                .build();

        category = categoryRepository.save(category);

        return map(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {

        return map(categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found")));
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(request.getCategoryName());
        category.setDescription(request.getDescription());

        return map(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepository.deleteById(id);

    }

    @Override
    public List<CategoryResponse> searchCategory(String name) {

        return categoryRepository.findByCategoryNameContainingIgnoreCase(name)
                .stream()
                .map(this::map)
                .toList();
    }

    private CategoryResponse map(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .description(category.getDescription())
                .active(category.getActive())
                .build();
    }
}