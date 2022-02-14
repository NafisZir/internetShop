package com.example.myShop.controller;

import com.example.myShop.domain.dto.selectedProduct.SelectedProductCreateDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductInfoDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductUpdateDto;
import com.example.myShop.domain.exception.SelectedProductNotFoundException;
import com.example.myShop.domain.mapper.SelectedProductMapper;
import com.example.myShop.service.SelectedProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author nafis
 * @since 10.02.2022
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SelectedProductController {
    private final SelectedProductService selectedProductService;
    private final SelectedProductMapper selectedProductMapper;

    @GetMapping("selected-products/{selectedProductId}")
    public SelectedProductDto get(@PathVariable("selectedProductId") Integer id){
        return Optional.of(id)
                .map(selectedProductService::getAndInitialize)
                .map(selectedProductMapper::toDto)
                .orElseThrow(() -> new SelectedProductNotFoundException(id));
    }

    @GetMapping("selected-products/info/{selectedProductId}")
    public SelectedProductInfoDto getInfo(@PathVariable("selectedProductId") Integer id){
        return Optional.of(id)
                .map(selectedProductService::getAndInitialize)
                .map(selectedProductMapper::toSelectedProductInfoDto)
                .orElseThrow(() -> new SelectedProductNotFoundException(id));
    }

    @GetMapping("orders/{orderId}/selected-products")
    public Page<SelectedProductDto> getAll(@PathVariable("orderId") Integer orderId, Pageable pageable){
        return Optional.of(orderId)
                .map(id -> selectedProductService.getAndInitializeAll(pageable, id))
                .map(it -> it.map(selectedProductMapper::toDto))
                .orElseThrow();
    }

    @PostMapping("users/{userId}/selected-products")
    public SelectedProductDto create(@Valid @RequestBody SelectedProductCreateDto selectedProductDto,
                                     @PathVariable("userId") Integer userId,
                                     @RequestParam("goodsId") Integer goodsId){
        return Optional.ofNullable(selectedProductDto)
                .map(selectedProductMapper::fromCreateDto)
                .map(toCreate -> selectedProductService.create(toCreate, goodsId, userId))
                .map(selectedProductMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("selected-products/{id}")
    public SelectedProductDto update(@PathVariable("id") Integer id,
                                     @RequestBody SelectedProductUpdateDto selectedProductDto){
        return Optional.ofNullable(selectedProductDto)
                .map(selectedProductMapper::fromUpdateDto)
                .map(toUpdate -> selectedProductService.update(id, toUpdate))
                .map(selectedProductMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("selected-products/{id}")
    public void delete(@PathVariable("id") Integer id){
        selectedProductService.delete(id);
    }
}
