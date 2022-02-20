package com.example.myShop.controller;

import com.example.myShop.domain.dto.collectionWrapper.CollectionWrapperDto;
import com.example.myShop.domain.dto.billStatus.BillStatusDto;
import com.example.myShop.domain.mapper.BillStatusMapper;
import com.example.myShop.service.BillStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Billing status", description = "Operations with billing status of order")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Bad request")
@RequestMapping(path = "bill-statuses")
public class BillStatusController {
    private final BillStatusService billStatusService;
    private final BillStatusMapper billStatusMapper;

    @Operation(description = "Find all billing statuses")
    @ApiResponse(responseCode = "200", description = "Billing statuses found successfully")
    @GetMapping
    public CollectionWrapperDto<BillStatusDto> getAll() {
        return Optional.of(billStatusService.getAllAndWrap())
                .map(billStatusMapper::toWrapperDto)
                .orElseThrow();
    }
}
