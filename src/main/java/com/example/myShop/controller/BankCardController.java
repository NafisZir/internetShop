package com.example.myShop.controller;

import com.example.myShop.domain.dto.bankCard.BankCardCreateDto;
import com.example.myShop.domain.dto.bankCard.BankCardDto;
import com.example.myShop.domain.dto.bankCard.BankCardInfoDto;
import com.example.myShop.domain.dto.bankCard.BankCardUpdateDto;
import com.example.myShop.domain.exception.BankCardNotFoundException;
import com.example.myShop.domain.mapper.BankCardMapper;
import com.example.myShop.service.BankCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

/**
 * @author nafis
 * @since 10.02.2022
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Bank card", description = "Operations with bank card of users")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "404", description = "Bank card not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "400", description = "Validation failed or bad request")
public class BankCardController {
    private final BankCardService bankCardService;
    private final BankCardMapper bankCardMapper;

    @Operation(description = "Find bank card by id")
    @ApiResponse(responseCode = "200", description = "Bank card found successfully")
    @GetMapping("bank-cards/{id}")
    public BankCardDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(bankCardService::getAndInitialize)
                .map(bankCardMapper::toDto)
                .orElseThrow(() -> new BankCardNotFoundException(id));
    }

    @Operation(description = "Find information about bank card by id")
    @ApiResponse(responseCode = "200", description = "Bank card found successfully")
    @GetMapping("bank-cards/info/{id}")
    public BankCardInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(bankCardService::getAndInitialize)
                .map(bankCardMapper::toBankCardInfoDto)
                .orElseThrow(() -> new BankCardNotFoundException(id));
    }

    @Operation(description = "Find all bank cards by user id")
    @ApiResponse(responseCode = "200", description = "Bank cards found successfully")
    @GetMapping("users/{userId}/bank-cards")
    public Page<BankCardDto> getAll(@PathVariable("userId") Integer userId, Pageable pageable){
       return Optional.of(userId)
               .map(id -> bankCardService.getAndInitializeAll(pageable, id))
               .map(it -> it.map(bankCardMapper::toDto))
               .orElseThrow();
    }

    @Operation(description = "Create bank card for user by id")
    @ApiResponse(responseCode = "200", description = "Bank card created successfully")
    @PostMapping("users/{userId}/bank-cards")
    public BankCardDto create(@Valid @RequestBody BankCardCreateDto bankCardDto,
                              @PathVariable("userId") Integer userId){
        return Optional.ofNullable(bankCardDto)
                .map(bankCardMapper::fromCreateDto)
                .map(toCreate -> bankCardService.create(toCreate, userId))
                .map(bankCardMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Update bank card by id")
    @ApiResponse(responseCode = "200", description = "Bank card updated successfully")
    @PatchMapping("bank-cards/{id}")
    public BankCardDto update(@PathVariable("id") Integer id, @RequestBody BankCardUpdateDto bankCardDto){
        return Optional.ofNullable(bankCardDto)
                .map(bankCardMapper::fromUpdateDto)
                .map(toUpdate -> bankCardService.update(id, toUpdate))
                .map(bankCardMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Remove bank card by id")
    @ApiResponse(responseCode = "204", description = "Bank card removed successfully")
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("bank-cards/{id}")
    public void delete(@PathVariable("id") Integer id){
        bankCardService.delete(id);
    }
}
