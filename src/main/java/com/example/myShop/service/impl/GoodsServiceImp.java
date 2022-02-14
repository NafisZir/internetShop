package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Goods;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodsMapper;
import com.example.myShop.repository.GoodsRepository;
import com.example.myShop.service.CategoryService;
import com.example.myShop.service.GoodsService;
import com.example.myShop.service.ProducerService;
import com.example.myShop.utils.InitProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@Transactional
@RequiredArgsConstructor
public class GoodsServiceImp implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsMapper goodsMapper;
    private final CategoryService categoryService;
    private final ProducerService producerService;

    @Override
    public Goods get(Integer id) {
        return goodsRepository.findById(id).orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @Override
    public Goods getAndInitialize(Integer id) {
        return Optional.of(id)
                .map(goodsRepository::findById)
                .get()
                .map(InitProxy::initGoods)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @Override
    public Page<Goods> getAndInitializeAll(Pageable pageable){
        Page<Goods> goodsPage = goodsRepository.findAll(pageable);
        List<Goods> list = new ArrayList<>();

        for(Goods goods : goodsPage){
            list.add(InitProxy.initGoods(goods));
        }

        return new PageImpl<>(list);
    }

    @Override
    public Page<Goods> getAllByCategoryIdAndInit(Pageable pageable, Integer categoryId){
        Page<Goods> goodsPage = goodsRepository.findAllByCategoryId(pageable, categoryId);
        List<Goods> list = new ArrayList<>();

        for(Goods goods : goodsPage){
            list.add(InitProxy.initGoods(goods));
        }

        return new PageImpl<>(list);
    }

    @Override
    public Page<Goods> getAllByProducerIdAndInit(Pageable pageable, Integer producerId){
        Page<Goods> goodsPage = goodsRepository.findAllByProducerId(pageable, producerId);
        List<Goods> list = new ArrayList<>();

        for(Goods goods : goodsPage){
            list.add(InitProxy.initGoods(goods));
        }

        return new PageImpl<>(list);
    }

    @Override
    public Goods create(Goods goods, Integer categoryId, Integer producerId) {
        goods.setCategory(categoryService.get(categoryId));
        goods.setProducer(producerService.get(producerId));

        return goodsRepository.save(goods);
    }

    @Override
    public Goods update(Integer id, Goods goods) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> goodsMapper.merge(current, goods))
                .map(goodsRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        goodsRepository.deleteById(id);
    }
}
