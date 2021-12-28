package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.mapper.GoodMapper;
import com.example.myShop.repository.GoodsRepository;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return goodsRepository.findById(id).orElse(null);
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

    public List<Goods> getGoods(String name) {
        if (name != null) return goodsRepository.findByName(name);
        return goodsRepository.findAll();
    }

    public List<Goods> getGoodsByCategory(Integer id){
        return goodsRepository.findByCategoryId(id);
    }
}
