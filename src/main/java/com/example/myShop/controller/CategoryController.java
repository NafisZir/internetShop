package com.example.myShop.controller;

import com.example.myShop.domain.dto.category.CategoryDto;
import com.example.myShop.domain.dto.category.CategoryCreateDto;
import com.example.myShop.domain.dto.category.CategoryInfoDto;
import com.example.myShop.domain.dto.category.CategoryUpdateDto;
import com.example.myShop.domain.entity.Category;
import com.example.myShop.domain.exception.CategoryNotFoundException;
import com.example.myShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.myShop.domain.mapper.CategoryMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;


    @GetMapping("{id}")
    public CategoryDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(categoryService::get)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @GetMapping("/info/{id}")
    public CategoryInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(categoryService::get)
                .map(categoryMapper::toInfoDto)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @GetMapping("/index")
    public List<Category> index(){
        return categoryService.getAll();
    }

    @PostMapping()
    public CategoryDto create(@RequestBody CategoryCreateDto categoryDto){
        return Optional.ofNullable(categoryDto)
                .map(categoryMapper::fromCreateDto)
                .map(categoryService::create)
                .map(categoryMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public CategoryDto update(@PathVariable("id") Integer id, @RequestBody CategoryUpdateDto categoryDto){
        return Optional.ofNullable(categoryDto)
                .map(categoryMapper::fromUpdateDto)
                .map(toUpdate -> categoryService.update(id, toUpdate))
                .map(categoryMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
    }
}
