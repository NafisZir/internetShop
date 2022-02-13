package com.example.myShop.service.impl;

import com.example.myShop.domain.wrapper.CollectionWrapper;
import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.service.BillStatusService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author nafis
 * @since 12.02.2022
 */

@Service
public class BillStatusServiceImpl implements BillStatusService {
    @Override
    public Set<BillStatus> getAll() {
        return EnumSet.allOf(BillStatus.class);
    }

    @Override
    public CollectionWrapper<BillStatus> getAllAndWrap() {
        return new CollectionWrapper<>(EnumSet.allOf(BillStatus.class));
    }
}
