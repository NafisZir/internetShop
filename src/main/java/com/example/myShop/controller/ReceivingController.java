package com.example.myShop.controller;

import com.example.myShop.domain.dto.ReceivingDto;
import com.example.myShop.domain.dto.ReceivingNotIdDto;
import com.example.myShop.domain.mapper.ReceivingMapper;
import com.example.myShop.service.ReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Controller
@RequestMapping(path = "receivings")
@RequiredArgsConstructor
public class ReceivingController {
    private final ReceivingService receivingService;
    private final ReceivingMapper receivingMapper;

    @GetMapping("{id}")
    public ReceivingDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(receivingService::get)
                .map(receivingMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Receiving method not found:" + id));
    }

    @PostMapping()
    public ReceivingDto create(@RequestBody ReceivingNotIdDto receivingDto){
        return Optional.ofNullable(receivingDto)
                .map(receivingMapper::fromNotIdDto)
                .map(receivingService::create)
                .map(receivingMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public ReceivingDto update(@PathVariable("id") Integer id,@RequestBody ReceivingNotIdDto receivingDto){
        return Optional.ofNullable(receivingDto)
                .map(receivingMapper::fromNotIdDto)
                .map(toUpdate -> receivingService.update(toUpdate, id))
                .map(receivingMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        receivingService.delete(id);
    }
}
