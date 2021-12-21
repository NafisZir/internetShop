package com.example.myShop.controller;

import com.example.myShop.domain.dto.GoodDto;
import com.example.myShop.domain.dto.GoodNotIdDto;
import com.example.myShop.domain.mapper.GoodMapper;
import com.example.myShop.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "goods")
public class GoodsController {
    private final GoodsService goodsService;
    private final GoodMapper goodMapper;

//    @GetMapping()
//    public String getGoods(@RequestParam(name = "name", required = false) String name, Model model, Principal principal) {
//        model.addAttribute("goods", goodsService.getGoods(name));
//        model.addAttribute("client", userService.getUserByPrincipal(principal));
//        return "goods";
//    }

    @GetMapping("/{goodId}")
    public GoodDto get(@PathVariable(name = "goodId") Integer id) {
        return Optional.of(id)
                .map(goodsService::get)
                .map(goodMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Goods not found " + id));
    }

    @PostMapping()
    public GoodDto create(@RequestBody GoodNotIdDto goodsDto){
        return Optional.ofNullable(goodsDto)
                .map(goodMapper::fromNotIdDto)
                .map(goodsService::create)
                .map(goodMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping ("/{goodId}")
    public GoodDto update(@PathVariable("goodId") Integer id, @RequestBody GoodNotIdDto goodsDto){
        return Optional.ofNullable(goodsDto)
                .map(goodMapper::fromNotIdDto)
                .map(toUpdate -> goodsService.update(id, toUpdate))
                .map(goodMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{goodId}")
    public void delete(@PathVariable("goodId") Integer id){
        goodsService.delete(id);
    }
}
