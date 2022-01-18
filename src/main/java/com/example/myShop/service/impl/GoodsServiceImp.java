package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodMapper;
import com.example.myShop.repository.GoodsRepository;
import com.example.myShop.service.CategoryService;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class GoodsServiceImp implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodMapper goodMapper;
    private final CategoryService categoryService;
    private final ProducerService producerService;

    public Goods get(Integer id) {
        return goodsRepository.findById(id).orElseThrow(() -> new GoodsNotFoundException(id));
    }

    public Map<String, Object> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<Goods> goodsPage = goodsRepository.findAll(pageable);

        Map<String, Object> response = new HashMap<>();

        response.put("goods", goodsPage.getContent());
        response.put("currentPage", goodsPage.getNumber());
        response.put("totalItems", goodsPage.getTotalElements());
        response.put("totalPages", goodsPage.getTotalPages());

        return response;
    }

    @Override
    public Goods create(Goods goods, Integer categoryId, Integer producerId) {
        goods.setCategory(categoryService.get(categoryId));
        goods.setProducer(producerService.get(producerId));

        return goodsRepository.save(goods);
    }

    public Goods update(Integer id, Goods goods) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> goodMapper.merge(current, goods))
                .map(goodsRepository::save)
                .orElseThrow();
    }

    public void delete(Integer id) {
        goodsRepository.deleteById(id);
    }
}
