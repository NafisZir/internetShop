package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Category;
import com.example.myShop.domain.exception.BankCardNotFoundException;
import com.example.myShop.domain.mapper.CategoryMapper;
import com.example.myShop.repository.CategoryRepository;
import com.example.myShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Category get(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new BankCardNotFoundException(id));
    }

    @Override
    public Category getAndInitialize(Integer id) {
        Category result = categoryRepository.findById(id).orElseThrow(() -> new BankCardNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getGoods());
        return result;
    }

    @Override
    public Page<Category> getAndInitializeAll(Pageable pageable){
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        List<Category> list = new ArrayList<>();

        for(Category category : categoryPage){
            Hibernate.initialize(category);
            Hibernate.initialize(category.getGoods());

            list.add(category);
        }

        return new PageImpl<>(list);
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
