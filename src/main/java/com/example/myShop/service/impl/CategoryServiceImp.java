package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Category;
import com.example.myShop.domain.entity.Goods;
import com.example.myShop.repository.CategoryRepository;
import com.example.myShop.service.CategoryService;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final GoodsService goodsService;

    @Override
    public Category get(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Integer id, Category category){
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        List<Goods> goodsList = goodsService.getGoodsByCategory(id);
        if(goodsList.isEmpty()){
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
