package com.example.myShop.service;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.BillStatus;

import java.util.Set;

/**
 * @author nafis
 * @since 12.02.2022
 */
public interface BillStatusService {
    /**
     * Find from BillStatus enum all bill statuses
     * @return set of all bill statuses
     */
    Set<BillStatus> getAll();

    /**
     * Find from BillStatus enum all bill statuses and wrap to CollectionWrapper
     * @return wrapper of all bill statuses
     */
    CollectionWrapper<BillStatus> getAllAndWrap();
}
