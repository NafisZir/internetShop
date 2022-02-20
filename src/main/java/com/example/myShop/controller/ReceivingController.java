package com.example.myShop.controller;

import com.example.myShop.domain.dto.receiving.ReceivingCreateDto;
import com.example.myShop.domain.dto.receiving.ReceivingDto;
import com.example.myShop.domain.dto.receiving.ReceivingInfoDto;
import com.example.myShop.domain.dto.receiving.ReceivingUpdateDto;
import com.example.myShop.domain.exception.ReceivingNotFoundException;
import com.example.myShop.domain.mapper.ReceivingMapper;
import com.example.myShop.service.ReceivingService;
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
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "receivings")
@RequiredArgsConstructor
@Tag(name = "Receiving", description = "Operations with receiving type of order")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "Receiving type not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class ReceivingController {
    private final ReceivingService receivingService;
    private final ReceivingMapper receivingMapper;

    @Operation(description = "Find receiving type by id")
    @ApiResponse(responseCode = "200", description = "Receiving type found successfully")
    @GetMapping("{id}")
    public ReceivingDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(receivingService::getAndInitialize)
                .map(receivingMapper::toDto)
                .orElseThrow(() -> new ReceivingNotFoundException(id));
    }

    @Operation(description = "Find information about receiving type by id")
    @ApiResponse(responseCode = "200", description = "Receiving type found successfully")
    @GetMapping("/info/{id}")
    public ReceivingInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(receivingService::getAndInitialize)
                .map(receivingMapper::toInfoDto)
                .orElseThrow(() -> new ReceivingNotFoundException(id));
    }

    @Operation(description = "Find all receiving types. Returns page")
    @ApiResponse(responseCode = "200", description = "Receiving types found successfully")
    @GetMapping()
    public Page<ReceivingDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(receivingService::getAndInitializeAll)
                .map(it -> it.map(receivingMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Create new receiving type")
    @ApiResponse(responseCode = "200", description = "Receiving type created successfully")
    @PostMapping()
    public ReceivingDto create(@Valid @RequestBody ReceivingCreateDto receivingDto){
        return Optional.ofNullable(receivingDto)
                .map(receivingMapper::fromCreateDto)
                .map(receivingService::create)
                .map(receivingMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Update receiving type by id")
    @ApiResponse(responseCode = "200", description = "Receiving type updated successfully")
    @PatchMapping("/{id}")
    public ReceivingDto update(@PathVariable("id") Integer id,@RequestBody ReceivingUpdateDto receivingDto){
        return Optional.ofNullable(receivingDto)
                .map(receivingMapper::fromUpdateDto)
                .map(toUpdate -> receivingService.update(toUpdate, id))
                .map(receivingMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Remove receiving type by id")
    @ApiResponse(responseCode = "204", description = "Receiving type removed successfully")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        receivingService.delete(id);
    }
}
