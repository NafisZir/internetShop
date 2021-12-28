package com.example.myShop.controller;

import com.example.myShop.domain.dto.goods.GoodCreateDto;
import com.example.myShop.domain.dto.goods.GoodDto;
import com.example.myShop.domain.dto.goods.GoodsUpdateDto;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodMapper;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final GoodMapper goodMapper;

    @GetMapping("/{goodId}")
    public GoodDto get(@PathVariable(name = "goodId") Integer id) {
        return Optional.of(id)
                .map(goodsService::get)
                .map(goodMapper::toDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @PostMapping()
    public GoodDto create(@RequestBody GoodCreateDto goodsDto){
        return Optional.ofNullable(goodsDto)
                .map(goodMapper::fromCreateDto)
                .map(goodsService::create)
                .map(goodMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping ("/{goodId}")
    public GoodDto update(@PathVariable("goodId") Integer id, @RequestBody GoodsUpdateDto goodsDto){
        return Optional.ofNullable(goodsDto)
                .map(goodMapper::fromUpdateDto)
                .map(toUpdate -> goodsService.update(id, toUpdate))
                .map(goodMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{goodId}")
    public void delete(@PathVariable("goodId") Integer id){
        goodsService.delete(id);
    }
}
