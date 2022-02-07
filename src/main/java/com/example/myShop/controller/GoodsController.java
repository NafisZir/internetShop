package com.example.myShop.controller;

import com.example.myShop.domain.dto.goods.GoodCreateDto;
import com.example.myShop.domain.dto.goods.GoodDto;
import com.example.myShop.domain.dto.goods.GoodsInfoDto;
import com.example.myShop.domain.dto.goods.GoodsUpdateDto;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodMapper;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
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

    @GetMapping("/info/{id}")
    public GoodsInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(goodsService::get)
                .map(goodMapper::toInfoDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size){
        Map<String, Object> response = goodsService.getAll(page, size);

        try{
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
