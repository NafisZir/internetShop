package com.example.myShop.services;

import com.example.myShop.models.Client;
import com.example.myShop.models.Goods;
import com.example.myShop.repositories.ClientRepository;
import com.example.myShop.repositories.GoodsRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final ClientRepository clientRepository;

    public GoodsService(GoodsRepository goodsRepository, ClientRepository clientRepository) {
        this.goodsRepository = goodsRepository;
        this.clientRepository = clientRepository;
    }

    public List<Goods> listGoods(String name) {
        if (name != null) return goodsRepository.findByName(name);
        return goodsRepository.findAll();
    }

    public void saveGoods(Goods goods) {
        goodsRepository.save(goods);
    }

    public void deleteGoods(Integer id) {
        goodsRepository.deleteById(id);
    }

    public Goods getGoodsById(Integer id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public List<Goods> getGoodsListByCategory(Integer id){
        return goodsRepository.findByCategoryID(id);
    }
}
