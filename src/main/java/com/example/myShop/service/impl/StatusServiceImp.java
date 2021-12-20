package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.Status;
import com.example.myShop.repository.StatusRepository;
import com.example.myShop.service.OrderService;
import com.example.myShop.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class StatusServiceImp implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public Status getPrimaryStatus(){
        return statusRepository.findById("In stock").orElse(null);
    }

    @Override
    public List<Status> getStatuses(){
        return statusRepository.findAll();
    }

    @Override
    public void create(Status status){
        statusRepository.save(status);
    }

    @Override
    public void delete(String id){
        statusRepository.deleteById(id);
    }
}
