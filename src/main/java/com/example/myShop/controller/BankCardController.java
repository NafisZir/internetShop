package com.example.myShop.controller;

import com.example.myShop.domain.dto.bankCard.BankCardCreateDto;
import com.example.myShop.domain.dto.bankCard.BankCardDto;
import com.example.myShop.domain.dto.bankCard.BankCardInfoDto;
import com.example.myShop.domain.dto.bankCard.BankCardUpdateDto;
import com.example.myShop.domain.exception.BankCardNotFoundException;
import com.example.myShop.domain.mapper.BankCardMapper;
import com.example.myShop.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * @author nafis
 * @since 10.02.2022
 */

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BankCardController {
    private final BankCardService bankCardService;
    private final BankCardMapper bankCardMapper;

    @GetMapping("bank-cards/{id}")
    public BankCardDto get(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(bankCardService::getAndInitialize)
                .map(bankCardMapper::toDto)
                .orElseThrow(() -> new BankCardNotFoundException(id));
    }

    @GetMapping("bank-cards/info/{id}")
    public BankCardInfoDto getInfo(@PathVariable("id") Integer id){
        return Optional.of(id)
                .map(bankCardService::getAndInitialize)
                .map(bankCardMapper::toInfoDto)
                .orElseThrow(() -> new BankCardNotFoundException(id));
    }

    @GetMapping("users/{userId}/bank-cards")
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam("page") Integer page,
                                                      @RequestParam("size") Integer size,
                                                      @PathVariable("userId") Integer userId){
        Map<String, Object> response = bankCardService.getAndInitializeAll(page, size, userId);

        try {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("users/{userId}/bank-cards")
    public BankCardDto create(@Valid @RequestBody BankCardCreateDto bankCardDto,
                              @PathVariable("userId") Integer userId){
        return Optional.ofNullable(bankCardDto)
                .map(bankCardMapper::fromCreateDto)
                .map(toCreate -> bankCardService.create(toCreate, userId))
                .map(bankCardMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("bank-cards/{id}")
    public BankCardDto update(@PathVariable("id") Integer id, @RequestBody BankCardUpdateDto bankCardDto){
        return Optional.ofNullable(bankCardDto)
                .map(bankCardMapper::fromUpdateDto)
                .map(toUpdate -> bankCardService.update(id, toUpdate))
                .map(bankCardMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("bank-cards/{id}")
    public void delete(@PathVariable("id") Integer id){
        bankCardService.delete(id);
    }
}
