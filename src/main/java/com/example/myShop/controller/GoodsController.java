package com.example.myShop.controller;

import com.example.myShop.domain.dto.goods.GoodsCreateDto;
import com.example.myShop.domain.dto.goods.GoodsDto;
import com.example.myShop.domain.dto.goods.GoodsInfoDto;
import com.example.myShop.domain.dto.goods.GoodsUpdateDto;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodsMapper;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping
public class GoodsController {
    private final GoodsService goodsService;
    private final GoodsMapper goodsMapper;

    @GetMapping("goods/{goodsId}")
    public GoodsDto get(@PathVariable(name = "goodsId") Integer id) {
        return Optional.of(id)
                .map(goodsService::getAndInitialize)
                .map(goodsMapper::toDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @GetMapping("goods/info/{id}")
    public GoodsInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(goodsService::getAndInitialize)
                .map(goodsMapper::toInfoDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @GetMapping("goods")
    public Page<GoodsDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(goodsService::getAndInitializeAll)
                .map(it -> it.map(goodsMapper::toDto))
                .orElseThrow();
    }

    @GetMapping("categories/{categoryId}/goods")
    public Page<GoodsDto> getAllByCategory(@PathVariable("categoryId") Integer categoryId,
                                           Pageable pageable){
        return Optional.of(categoryId)
                .map(id -> goodsService.getAllByCategoryIdAndInit(pageable, id))
                .map(it -> it.map(goodsMapper::toDto))
                .orElseThrow();
    }

    @GetMapping("producers/{producerId}/goods")
    public Page<GoodsDto> getAllByProducer(@PathVariable("producerId") Integer producerId,
                                           Pageable pageable){
        return Optional.of(producerId)
                .map(id -> goodsService.getAllByProducerIdAndInit(pageable, id))
                .map(it -> it.map(goodsMapper::toDto))
                .orElseThrow();
    }

    @PostMapping("goods")
    public GoodsDto create(@Valid @RequestBody GoodsCreateDto goodsDto,
                           @RequestParam("categoryId") Integer categoryId,
                           @RequestParam("producerId") Integer producerId){
        return Optional.ofNullable(goodsDto)
                .map(goodsMapper::fromCreateDto)
                .map(toCreate -> goodsService.create(toCreate, categoryId, producerId))
                .map(goodsMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping ("goods/{goodId}")
    public GoodsDto update(@PathVariable("goodId") Integer id, @RequestBody GoodsUpdateDto goodsDto){
        return Optional.ofNullable(goodsDto)
                .map(goodsMapper::fromUpdateDto)
                .map(toUpdate -> goodsService.update(id, toUpdate))
                .map(goodsMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("goods/{goodId}")
    public void delete(@PathVariable("goodId") Integer id){
        goodsService.delete(id);
    }
}
