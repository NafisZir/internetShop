package com.example.myShop.controller;

import com.example.myShop.domain.dto.producer.ProducerCreateDto;
import com.example.myShop.domain.dto.producer.ProducerDto;
import com.example.myShop.domain.dto.producer.ProducerInfoDto;
import com.example.myShop.domain.dto.producer.ProducerUpdateDto;
import com.example.myShop.domain.exception.ProducerNotFoundException;
import com.example.myShop.domain.mapper.ProducerMapper;
import com.example.myShop.service.ProducerService;
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
@RequestMapping(path = "producers")
@RequiredArgsConstructor
@Tag(name = "Producer", description = "Operations with producers of goods")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "Producer not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class ProducerController {
    private final ProducerService producerService;
    private final ProducerMapper producerMapper;

    @Operation(description = "Find producer by id")
    @ApiResponse(responseCode = "200", description = "Producer found successfully")
    @GetMapping("/{id}")
    public ProducerDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(producerService::getAndInitialize)
                .map(producerMapper::toDto)
                .orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @Operation(description = "Find information about producer by id")
    @ApiResponse(responseCode = "200", description = "Producer found successfully")
    @GetMapping("/info/{id}")
    public ProducerInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(producerService::getAndInitialize)
                .map(producerMapper::toInfoDto)
                .orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @Operation(description = "Find all producers. Returns page")
    @ApiResponse(responseCode = "200", description = "Producers found successfully")
    @GetMapping()
    public Page<ProducerDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(producerService::getAndInitializeAll)
                .map(it -> it.map(producerMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Create new producer")
    @ApiResponse(responseCode = "200", description = "Producer created successfully")
    @PostMapping()
    public ProducerDto create(@Valid @RequestBody ProducerCreateDto producerDto){
        return Optional.ofNullable(producerDto)
                .map(producerMapper::fromCreateDto)
                .map(producerService::create)
                .map(producerMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Update producer by id")
    @ApiResponse(responseCode = "200", description = "Producers updated successfully")
    @PatchMapping("/{id}")
    public ProducerDto update(@PathVariable("id") Integer id, @RequestBody ProducerUpdateDto producerDto){
        return Optional.ofNullable(producerDto)
                .map(producerMapper::fromUpdateDto)
                .map(toUpdate -> producerService.update(toUpdate, id))
                .map(producerMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Remove producer by id")
    @ApiResponse(responseCode = "204", description = "Producer removed successfully")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        producerService.delete(id);
    }
}
