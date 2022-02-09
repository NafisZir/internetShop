package com.example.myShop.controller;

import com.example.myShop.domain.dto.producer.ProducerCreateDto;
import com.example.myShop.domain.dto.producer.ProducerDto;
import com.example.myShop.domain.dto.producer.ProducerInfoDto;
import com.example.myShop.domain.dto.producer.ProducerUpdateDto;
import com.example.myShop.domain.exception.ProducerNotFoundException;
import com.example.myShop.domain.mapper.ProducerMapper;
import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "producers")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService producerService;
    private final ProducerMapper producerMapper;

    @GetMapping("/{id}")
    public ProducerDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(producerService::getAndInitialize)
                .map(producerMapper::toDto)
                .orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size){
        Map<String, Object> response = producerService.getAndInitializeAll(page, size);

        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info/{id}")
    public ProducerInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(producerService::getAndInitialize)
                .map(producerMapper::toInfoDto)
                .orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @PostMapping()
    public ProducerDto create(@Valid @RequestBody ProducerCreateDto producerDto){
        return Optional.ofNullable(producerDto)
                .map(producerMapper::fromCreateDto)
                .map(producerService::create)
                .map(producerMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public ProducerDto update(@PathVariable("id") Integer id, @RequestBody ProducerUpdateDto producerDto){
        return Optional.ofNullable(producerDto)
                .map(producerMapper::fromUpdateDto)
                .map(toUpdate -> producerService.update(toUpdate, id))
                .map(producerMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        producerService.delete(id);
    }
}
