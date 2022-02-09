package com.example.myShop.service.impl;

import com.example.myShop.domain.dto.category.CategoryDto;
import com.example.myShop.domain.entity.Category;
import com.example.myShop.domain.exception.CategoryNotFoundException;
import com.example.myShop.domain.mapper.CategoryMapper;
import com.example.myShop.repository.CategoryRepository;
import com.example.myShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category getAndInitialize(Integer id) {
        Category result = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getGoods());
        return result;
    }

    @Override
    public Map<String, Object> getAndInitializeAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<CategoryDto> listTemp = new ArrayList<>();

        for(Category category : categoryPage){
            Hibernate.initialize(category);
            Hibernate.initialize(category.getGoods());

            listTemp.add(categoryMapper.toDto(category));
        }

        Page<CategoryDto> result = new PageImpl<>(listTemp);
        Map<String, Object> response = new HashMap<>();

        response.put("categories", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return response;
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer id, Category category){
        return Optional.of(id)
                .map(this::getAndInitialize)
                .map(current -> categoryMapper.merge(current, category))
                .map(categoryRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
