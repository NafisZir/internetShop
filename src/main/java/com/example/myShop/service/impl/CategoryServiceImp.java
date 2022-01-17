package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Category;
import com.example.myShop.domain.exception.CategoryNotFoundException;
import com.example.myShop.domain.mapper.CategoryMapper;
import com.example.myShop.repository.CategoryRepository;
import com.example.myShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer id, Category category){
        return Optional.of(id)
                .map(this::get)
                .map(current -> categoryMapper.merge(current, category))
                .map(categoryRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
