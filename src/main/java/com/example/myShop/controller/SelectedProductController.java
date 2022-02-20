package com.example.myShop.controller;

import com.example.myShop.domain.dto.selectedProduct.SelectedProductCreateDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductInfoDto;
import com.example.myShop.domain.dto.selectedProduct.SelectedProductUpdateDto;
import com.example.myShop.domain.exception.SelectedProductNotFoundException;
import com.example.myShop.domain.mapper.SelectedProductMapper;
import com.example.myShop.service.SelectedProductService;
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
 * @since 10.02.2022
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Selected product", description = "Operations with products selected by the user during the assembly of the basket")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "Selected product not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class SelectedProductController {
    private final SelectedProductService selectedProductService;
    private final SelectedProductMapper selectedProductMapper;

    @Operation(description = "Find selected product by id")
    @ApiResponse(responseCode = "200", description = "Selected product found successfully")
    @GetMapping("selected-products/{selectedProductId}")
    public SelectedProductDto get(@PathVariable("selectedProductId") Integer id){
        return Optional.of(id)
                .map(selectedProductService::getAndInitialize)
                .map(selectedProductMapper::toDto)
                .orElseThrow(() -> new SelectedProductNotFoundException(id));
    }

    @Operation(description = "Find information about selected product by id")
    @ApiResponse(responseCode = "200", description = "Selected product found successfully")
    @GetMapping("selected-products/info/{selectedProductId}")
    public SelectedProductInfoDto getInfo(@PathVariable("selectedProductId") Integer id){
        return Optional.of(id)
                .map(selectedProductService::getAndInitialize)
                .map(selectedProductMapper::toSelectedProductInfoDto)
                .orElseThrow(() -> new SelectedProductNotFoundException(id));
    }

    @Operation(description = "Find all selected products by order id. Returns page")
    @ApiResponse(responseCode = "200", description = "Selected products found successfully")
    @GetMapping("orders/{orderId}/selected-products")
    public Page<SelectedProductDto> getAll(@PathVariable("orderId") Integer orderId, Pageable pageable){
        return Optional.of(orderId)
                .map(id -> selectedProductService.getAndInitializeAll(pageable, id))
                .map(it -> it.map(selectedProductMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Create new selected product")
    @ApiResponse(responseCode = "200", description = "Selected product created successfully")
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

    @Operation(description = "Update selected product by id")
    @ApiResponse(responseCode = "200", description = "Selected product updated successfully")
    @PatchMapping("selected-products/{id}")
    public SelectedProductDto update(@PathVariable("id") Integer id,
                                     @RequestBody SelectedProductUpdateDto selectedProductDto){
        return Optional.ofNullable(selectedProductDto)
                .map(selectedProductMapper::fromUpdateDto)
                .map(toUpdate -> selectedProductService.update(id, toUpdate))
                .map(selectedProductMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Remove selected product by id")
    @ApiResponse(responseCode = "204", description = "Selected product removed successfully")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("selected-products/{id}")
    public void delete(@PathVariable("id") Integer id){
        selectedProductService.delete(id);
    }
}
