package com.example.myShop.controller;

import com.example.myShop.domain.dto.StatusDto;
import com.example.myShop.domain.dto.StatusNotIdDto;
import com.example.myShop.domain.mapper.StatusMapper;
import com.example.myShop.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author nafis
 * @since 20.12.2021
 */

@Controller
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
    public StatusDto create(@RequestBody StatusNotIdDto statusDto){
        return Optional.ofNullable(statusDto)
                .map(statusMapper::fromNotIdDto)
                .map(statusService::create)
                .map(statusMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{id}")
    public StatusDto update(@PathVariable("id") Integer id,@RequestBody StatusNotIdDto statusDto){
        return Optional.ofNullable(statusDto)
                .map(statusMapper::fromNotIdDto)
                .map(toUpdate -> statusService.update(toUpdate, id))
                .map(statusMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        statusService.delete(id);
    }
}
