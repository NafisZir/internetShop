package com.example.myShop.controller;

import com.example.myShop.domain.entity.Category;
import com.example.myShop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Controller
@RequestMapping(path = "categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/admin")
    public String getCategories(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "admin-categories";
    }

    @PostMapping()
    public String create(Category category){
        categoryService.create(category);
        return "redirect:/categories/admin";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        model.addAttribute(categoryService.get(id));

        return "edit-category";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, Category category){
        Category category1 = categoryService.get(id);
        category1.setDescr(category.getDescr());
        categoryService.update(category1);
        return "redirect:/categories/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/categories/admin";
    }
}
