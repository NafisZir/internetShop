package com.example.myShop.controller;

import com.example.myShop.domain.dto.producer.ProducerCreateDto;
import com.example.myShop.domain.dto.producer.ProducerDto;
import com.example.myShop.domain.dto.producer.ProducerInfoDto;
import com.example.myShop.domain.dto.producer.ProducerUpdateDto;
import com.example.myShop.domain.entity.Producer;
import com.example.myShop.domain.exception.ProducerNotFoundException;
import com.example.myShop.domain.mapper.ProducerMapper;
import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
                .map(producerService::get)
                .map(producerMapper::toDto)
                .orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @GetMapping("/index")
    public List<Producer> index(){
        return producerService.getAll();
    }

    @GetMapping("/info/{id}")
    public ProducerInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(producerService::get)
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
