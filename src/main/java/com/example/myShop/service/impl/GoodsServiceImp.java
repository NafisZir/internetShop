package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodMapper;
import com.example.myShop.repository.GoodsRepository;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Goods get(Integer id) {
        return goodsRepository.findById(id).orElseThrow(() -> new GoodsNotFoundException(id));
    }

    public Goods create(Goods goods) {
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
