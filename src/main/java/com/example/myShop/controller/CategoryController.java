package com.example.myShop.controller;

import com.example.myShop.domain.dto.category.CategoryCreateDto;
import com.example.myShop.domain.dto.category.CategoryDto;
import com.example.myShop.domain.dto.category.CategoryInfoDto;
import com.example.myShop.domain.dto.category.CategoryUpdateDto;
import com.example.myShop.domain.exception.CategoryNotFoundException;
import com.example.myShop.domain.mapper.CategoryMapper;
import com.example.myShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/{id}")
    public CategoryDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(categoryService::getAndInitialize)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @GetMapping("/info/{id}")
    public CategoryInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(categoryService::getAndInitialize)
                .map(categoryMapper::toInfoDto)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size){
        Map<String, Object> response = categoryService.getAndInitializeAll(page, size);

        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public CategoryDto create(@Valid @RequestBody CategoryCreateDto categoryDto){
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
