package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Status;
import com.example.myShop.repository.StatusRepository;
import com.example.myShop.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class StatusServiceImp implements StatusService {
    private final StatusRepository statusRepository;

    @Override
    public Integer getPrimaryStatusId(){
        return statusRepository.findByStatus("In stock").getId();
    }

    @Override
    public Status get(Integer id){
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public Status create(Status status){
        return statusRepository.save(status);
    }

    public Status update(Status status, Integer id) {
        status.setId(id);
        return statusRepository.save(status);
    }

    @Override
    public void delete(Integer id){
        statusRepository.deleteById(id);
    }
}
