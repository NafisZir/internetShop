package com.example.myShop.controller;

import com.example.myShop.domain.dto.collectionWrapper.CollectionWrapperDto;
import com.example.myShop.domain.dto.billStatus.BillStatusDto;
import com.example.myShop.domain.mapper.BillStatusMapper;
import com.example.myShop.service.BillStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author nafis
 * @since 12.02.2022
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "bill-statuses")
public class BillStatusController {
    private final BillStatusService billStatusService;
    private final BillStatusMapper billStatusMapper;

    @GetMapping
    public CollectionWrapperDto<BillStatusDto> getAll() {
        return Optional.of(billStatusService.getAllAndWrap())
                .map(billStatusMapper::toWrapperDto)
                .orElseThrow();
    }
}
