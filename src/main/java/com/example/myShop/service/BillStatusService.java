package com.example.myShop.service;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.BillStatus;

import java.util.Set;

/**
 * @author nafis
 * @since 12.02.2022
 */
public interface BillStatusService {
    Set<BillStatus> getAll();

    CollectionWrapper<BillStatus> getAllAndWrap();
}
