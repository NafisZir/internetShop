package com.example.myShop.controller;

import com.example.myShop.domain.dto.goods.GoodsCreateDto;
import com.example.myShop.domain.dto.goods.GoodsDto;
import com.example.myShop.domain.dto.goods.GoodsInfoDto;
import com.example.myShop.domain.dto.goods.GoodsUpdateDto;
import com.example.myShop.domain.exception.GoodsNotFoundException;
import com.example.myShop.domain.mapper.GoodsMapper;
import com.example.myShop.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author nafis
 * @since 19.12.2021
 */

@RestController
@RequiredArgsConstructor
@RequestMapping
@Tag(name = "Goods", description = "Operations with goods")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "Goods not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class GoodsController {
    private final GoodsService goodsService;
    private final GoodsMapper goodsMapper;

    @Operation(description = "Find goods by id")
    @ApiResponse(responseCode = "200", description = "Goods found successfully")
    @GetMapping("goods/{goodsId}")
    public GoodsDto get(@PathVariable(name = "goodsId") Integer id) {
        return Optional.of(id)
                .map(goodsService::getAndInitialize)
                .map(goodsMapper::toDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @Operation(description = "Find information about goods by id")
    @ApiResponse(responseCode = "200", description = "Goods found successfully")
    @GetMapping("goods/info/{id}")
    public GoodsInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(goodsService::getAndInitialize)
                .map(goodsMapper::toInfoDto)
                .orElseThrow(() -> new GoodsNotFoundException(id));
    }

    @Operation(description = "Find all goods. Returns page")
    @ApiResponse(responseCode = "200", description = "Goods found successfully")
    @GetMapping("goods")
    public Page<GoodsDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(goodsService::getAndInitializeAll)
                .map(it -> it.map(goodsMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Find all goods by category id. Returns page")
    @ApiResponse(responseCode = "200", description = "Goods found successfully")
    @GetMapping("categories/{categoryId}/goods")
    public Page<GoodsDto> getAllByCategory(@PathVariable("categoryId") Integer categoryId,
                                           Pageable pageable){
        return Optional.of(categoryId)
                .map(id -> goodsService.getAllByCategoryIdAndInit(pageable, id))
                .map(it -> it.map(goodsMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Find all goods by producer id. Returns page")
    @ApiResponse(responseCode = "200", description = "Goods found successfully")
    @GetMapping("producers/{producerId}/goods")
    public Page<GoodsDto> getAllByProducer(@PathVariable("producerId") Integer producerId,
                                           Pageable pageable){
        return Optional.of(producerId)
                .map(id -> goodsService.getAllByProducerIdAndInit(pageable, id))
                .map(it -> it.map(goodsMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Create new goods")
    @ApiResponse(responseCode = "200", description = "Goods created successfully")
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

    @Operation(description = "Update goods by id")
    @ApiResponse(responseCode = "200", description = "Goods updated successfully")
    @PatchMapping ("goods/{goodId}")
    public GoodsDto update(@PathVariable("goodId") Integer id, @RequestBody GoodsUpdateDto goodsDto){
        return Optional.ofNullable(goodsDto)
                .map(goodsMapper::fromUpdateDto)
                .map(toUpdate -> goodsService.update(id, toUpdate))
                .map(goodsMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Remove goods by id")
    @ApiResponse(responseCode = "204", description = "Goods removed successfully")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("goods/{goodId}")
    public void delete(@PathVariable("goodId") Integer id){
        goodsService.delete(id);
    }
}
