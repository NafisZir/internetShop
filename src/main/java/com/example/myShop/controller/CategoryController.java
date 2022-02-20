package com.example.myShop.controller;

import com.example.myShop.domain.dto.category.CategoryCreateDto;
import com.example.myShop.domain.dto.category.CategoryDto;
import com.example.myShop.domain.dto.category.CategoryInfoDto;
import com.example.myShop.domain.dto.category.CategoryUpdateDto;
import com.example.myShop.domain.exception.CategoryNotFoundException;
import com.example.myShop.domain.mapper.CategoryMapper;
import com.example.myShop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "categories")
@Tag(name = "Category", description = "Operations with category of goods")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "Category not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(description = "Find category by id")
    @ApiResponse(responseCode = "200", description = "Category found successfully")
    @GetMapping("/{id}")
    public CategoryDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(categoryService::getAndInitialize)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Operation(description = "Find information about category by id")
    @ApiResponse(responseCode = "200", description = "Category found successfully")
    @GetMapping("/info/{id}")
    public CategoryInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(categoryService::getAndInitialize)
                .map(categoryMapper::toInfoDto)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Operation(description = "Find all categories")
    @ApiResponse(responseCode = "200", description = "Categories found successfully")
    @GetMapping()
    public Page<CategoryDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(categoryService::getAndInitializeAll)
                .map(it -> it.map(categoryMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Create new category")
    @ApiResponse(responseCode = "200", description = "Category created successfully")
    @PostMapping()
    public CategoryDto create(@Valid @RequestBody CategoryCreateDto categoryDto){
        return Optional.ofNullable(categoryDto)
                .map(categoryMapper::fromCreateDto)
                .map(categoryService::create)
                .map(categoryMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Update category by id")
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @PatchMapping("/{id}")
    public CategoryDto update(@PathVariable("id") Integer id, @RequestBody CategoryUpdateDto categoryDto){
        return Optional.ofNullable(categoryDto)
                .map(categoryMapper::fromUpdateDto)
                .map(toUpdate -> categoryService.update(id, toUpdate))
                .map(categoryMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Remove category by id")
    @ApiResponse(responseCode = "204", description = "Category removed successfully")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
    }
}
