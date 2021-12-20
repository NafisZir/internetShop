package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Category;
import com.example.myShop.domain.entity.Goods;
import com.example.myShop.repository.CategoryRepository;
import com.example.myShop.repository.GoodsRepository;
import com.example.myShop.service.CategoryService;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    public void create(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void update(Category category){
        categoryRepository.save(category);
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
