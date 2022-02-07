package com.example.myShop.service.impl;

import com.example.myShop.domain.dto.goods.GoodDto;
import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodMapper;
import com.example.myShop.repository.GoodsRepository;
import com.example.myShop.service.CategoryService;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.ProducerService;
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
public class GoodsServiceImp implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodMapper goodMapper;
    private final CategoryService categoryService;
    private final ProducerService producerService;

    public Goods get(Integer id) {
        Goods result =  goodsRepository.findById(id).orElseThrow(() -> new GoodsNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getOrders());
        Hibernate.initialize(result.getCategory());
        Hibernate.initialize(result.getProducer());
        return result;
    }

    public Map<String, Object> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Goods> goodsPage = goodsRepository.findAll(pageable);
        List<GoodDto> listTemp = new ArrayList<>();

        for(Goods goods : goodsPage){
            Hibernate.initialize(goods);
            Hibernate.initialize(goods.getOrders());
            Hibernate.initialize(goods.getCategory());
            Hibernate.initialize(goods.getProducer());

            listTemp.add(goodMapper.toDto(goods));
        }

        Page<GoodDto> result = new PageImpl<>(listTemp);
        Map<String, Object> response = new HashMap<>();

        response.put("goods", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

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
