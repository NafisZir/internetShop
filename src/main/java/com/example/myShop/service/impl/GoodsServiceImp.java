package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.entity.Order;
import com.example.myShop.repository.ClientRepository;
import com.example.myShop.repository.GoodsRepository;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class GoodsServiceImp implements GoodsService {
    private final GoodsRepository goodsRepository;

    public Goods get(Integer id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public void create(Goods goods) {
        goodsRepository.save(goods);
    }

    public void update(Goods goods) { goodsRepository.save(goods); }

    public void delete(Integer id) {
        goodsRepository.deleteById(id);
    }

    public List<Goods> getGoods(String name) {
        if (name != null) return goodsRepository.findByName(name);
        return goodsRepository.findAll();
    }

    public List<Goods> getGoodsByCategory(Integer id){
        return goodsRepository.findByCategoryID(id);
    }
}
