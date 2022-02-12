package com.example.myShop.controller;

import com.example.myShop.domain.dto.goods.GoodCreateDto;
import com.example.myShop.domain.dto.goods.GoodDto;
import com.example.myShop.domain.dto.goods.GoodsInfoDto;
import com.example.myShop.domain.dto.goods.GoodsUpdateDto;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodMapper;
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
@RequestMapping(path = "goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final GoodMapper goodMapper;

    @GetMapping("/{goodId}")
    public GoodDto get(@PathVariable(name = "goodId") Integer id) {
        return Optional.of(id)
                .map(goodsService::getAndInitialize)
                .map(goodMapper::toDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @GetMapping("/info/{id}")
    public GoodsInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(goodsService::getAndInitialize)
                .map(goodMapper::toInfoDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @GetMapping()
    public Page<GoodDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(goodsService::getAndInitializeAll)
                .map(it -> it.map(goodMapper::toDto))
                .orElseThrow();
    }

    @PostMapping()
    public GoodDto create(@Valid @RequestBody GoodCreateDto goodsDto,
                          @RequestParam("categoryId") Integer categoryId,
                          @RequestParam("producerId") Integer producerId){
        return Optional.ofNullable(goodsDto)
                .map(goodMapper::fromCreateDto)
                .map(toCreate -> goodsService.create(toCreate, categoryId, producerId))
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
