package com.example.myShop.controller;

import com.example.myShop.domain.dto.bankCard.BankCardCreateDto;
import com.example.myShop.domain.dto.bankCard.BankCardDto;
import com.example.myShop.domain.dto.bankCard.BankCardInfoDto;
import com.example.myShop.domain.dto.bankCard.BankCardUpdateDto;
import com.example.myShop.domain.exception.BankCardNotFoundException;
import com.example.myShop.domain.mapper.BankCardMapper;
import com.example.myShop.service.BankCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Page<BankCardDto> getAll(@PathVariable("userId") Integer userId, Pageable pageable){
       return Optional.of(userId)
               .map(id -> bankCardService.getAndInitializeAll(pageable, id))
               .map(it -> it.map(bankCardMapper::toDto))
               .orElseThrow();
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
