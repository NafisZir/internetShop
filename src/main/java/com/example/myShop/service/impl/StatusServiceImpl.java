package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Status;
import com.example.myShop.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author nafis
 * @since 05.02.2022
 */

@Service
public class StatusServiceImpl implements StatusService {

    @Override
    public List<String> getAll() {
        EnumSet<Status> enumSet = EnumSet.allOf(Status.class);
        List<String> result = new ArrayList<>();

        for(Status status : enumSet){
            result.add(status.getStatus());
        }

        return result;
    }
}
