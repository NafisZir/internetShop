package com.example.myShop.controller;

import com.example.myShop.domain.dto.receiving.ReceivingCreateDto;
import com.example.myShop.domain.dto.receiving.ReceivingDto;
import com.example.myShop.domain.dto.receiving.ReceivingInfoDto;
import com.example.myShop.domain.dto.receiving.ReceivingUpdateDto;
import com.example.myShop.domain.exception.ReceivingNotFoundException;
import com.example.myShop.domain.mapper.ReceivingMapper;
import com.example.myShop.service.ReceivingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "receivings")
@RequiredArgsConstructor
public class ReceivingController {
    private final ReceivingService receivingService;
    private final ReceivingMapper receivingMapper;

    @GetMapping("{id}")
    public ReceivingDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(receivingService::getAndInitialize)
                .map(receivingMapper::toDto)
                .orElseThrow(() -> new ReceivingNotFoundException(id));
    }

    @GetMapping("/info/{id}")
    public ReceivingInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(receivingService::getAndInitialize)
                .map(receivingMapper::toInfoDto)
                .orElseThrow(() -> new ReceivingNotFoundException(id));
    }

    @GetMapping()
    public Page<ReceivingDto> getAll(Pageable pageable){
        return Optional.of(pageable)
                .map(receivingService::getAndInitializeAll)
                .map(it -> it.map(receivingMapper::toDto))
                .orElseThrow();
    }

    @PostMapping()
    public ReceivingDto create(@Valid @RequestBody ReceivingCreateDto receivingDto){
        return Optional.ofNullable(receivingDto)
                .map(receivingMapper::fromCreateDto)
                .map(receivingService::create)
                .map(receivingMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public ReceivingDto update(@PathVariable("id") Integer id,@RequestBody ReceivingUpdateDto receivingDto){
        return Optional.ofNullable(receivingDto)
                .map(receivingMapper::fromUpdateDto)
                .map(toUpdate -> receivingService.update(toUpdate, id))
                .map(receivingMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        receivingService.delete(id);
    }
}
