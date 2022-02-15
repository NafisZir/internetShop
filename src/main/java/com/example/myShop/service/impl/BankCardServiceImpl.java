package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.BankCard;
import com.example.myShop.domain.exception.BankCardNotFoundException;
import com.example.myShop.domain.mapper.BankCardMapper;
import com.example.myShop.repository.BankCardRepository;
import com.example.myShop.service.BankCardService;
import com.example.myShop.service.UserService;
import com.example.myShop.utils.InitProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public BankCard get(Integer id){
        return bankCardRepository.findById(id).orElseThrow(() -> new BankCardNotFoundException(id));
    }

    @Override
    public BankCard getAndInitialize(Integer id) {
        return Optional.of(id)
                .map(bankCardRepository::findById)
                .get()
                .map(InitProxy::initBankCard)
                .orElseThrow(() -> new BankCardNotFoundException(id));
    }

    @Override
    public Page<BankCard> getAndInitializeAll(Pageable pageable, Integer userId) {
        Page<BankCard> bankCardPage = bankCardRepository.findAllByUserId(pageable, userId);
        List<BankCard> list = new ArrayList<>();

        for(BankCard bankCard : bankCardPage){
            list.add(InitProxy.initBankCard(bankCard));
        }

        return new PageImpl<>(list);
    }

    @Override
    public BankCard create(BankCard bankCard, Integer userId) {
        bankCard.setUser(userService.get(userId));
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
