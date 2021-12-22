package com.example.myShop.controller;

import com.example.myShop.domain.dto.status.StatusDto;
import com.example.myShop.domain.dto.status.StatusCreateDto;
import com.example.myShop.domain.dto.status.StatusUpdateDto;
import com.example.myShop.domain.mapper.StatusMapper;
import com.example.myShop.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "statuses")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;
    private final StatusMapper statusMapper;

    @GetMapping("{id}")
    public StatusDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(statusService::get)
                .map(statusMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Receiving method not found:" + id));
    }

    @PostMapping()
    public StatusDto create(@RequestBody StatusCreateDto statusDto){
        return Optional.ofNullable(statusDto)
                .map(statusMapper::fromCreateDto)
                .map(statusService::create)
                .map(statusMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public StatusDto update(@PathVariable("id") Integer id,@RequestBody StatusUpdateDto statusDto){
        return Optional.ofNullable(statusDto)
                .map(statusMapper::fromUpdateDto)
                .map(toUpdate -> statusService.update(toUpdate, id))
                .map(statusMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        statusService.delete(id);
    }
}
