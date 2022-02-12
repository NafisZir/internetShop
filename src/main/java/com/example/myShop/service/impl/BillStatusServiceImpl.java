package com.example.myShop.service.impl;

import com.example.myShop.domain.enums.BillStatus;
import com.example.myShop.service.BillStatusService;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author nafis
 * @since 12.02.2022
 */
public class BillStatusServiceImpl implements BillStatusService {
    @Override
    public List<String> getAll() {
        EnumSet<BillStatus> enumSet = EnumSet.allOf(BillStatus.class);
        List<String> result = new ArrayList<>();

        for(BillStatus billStatus : enumSet){
            result.add(billStatus.getStatus());
        }

        return result;
    }
}
