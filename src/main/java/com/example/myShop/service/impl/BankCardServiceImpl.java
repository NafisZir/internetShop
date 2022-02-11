package com.example.myShop.service.impl;

import com.example.myShop.domain.dto.bankCard.BankCardDto;
import com.example.myShop.domain.entity.BankCard;
import com.example.myShop.domain.exception.BankCardNotFoundException;
import com.example.myShop.domain.mapper.BankCardMapper;
import com.example.myShop.repository.BankCardRepository;
import com.example.myShop.service.BankCardService;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author nafis
 * @since 09.02.2022
 */

@Service
@Transactional
@RequiredArgsConstructor
public class BankCardServiceImpl implements BankCardService {
    private final BankCardRepository bankCardRepository;
    private final BankCardMapper bankCardMapper;
    private final UserService userService;

    @Override
    public BankCard getAndInitialize(Integer id) {
        BankCard result = bankCardRepository.findById(id).orElseThrow(() -> new BankCardNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getUser());
        return result;
    }

    @Override
    public Map<String, Object> getAndInitializeAll(int page, int size, Integer userId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BankCard> bankCardPage = bankCardRepository.findAllByUserId(pageable, userId);
        List<BankCardDto> listTemp = new ArrayList<>();

        for(BankCard bankCard : bankCardPage){
            Hibernate.initialize(bankCard);
            Hibernate.initialize(bankCard.getUser());

            listTemp.add(bankCardMapper.toDto(bankCard));
        }

        Page<BankCardDto> result = new PageImpl<>(listTemp);
        Map<String, Object> response = new HashMap<>();

        response.put("bankCards", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

        return response;
    }

    @Override
    public BankCard create(BankCard bankCard, Integer userId) {
        bankCard.setUser(userService.getAndInitialize(userId));
        return bankCardRepository.save(bankCard);
    }

    @Override
    public BankCard update(Integer id, BankCard bankCard) {
        return Optional.of(id)
                .map(this::getAndInitialize)
                .map(current -> bankCardMapper.merge(current, bankCard))
                .map(bankCardRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(Integer id) {
        bankCardRepository.deleteById(id);
    }
}
