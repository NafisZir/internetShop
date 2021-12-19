package com.example.myShop.services;

import com.example.myShop.models.Status;
import com.example.myShop.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getPrimaryStatus(){
        return statusRepository.findById("In stock").orElse(null);
    }

    public List<Status> getStatusList(){
        return statusRepository.findAll();
    }

    public void saveStatus(Status status){
        statusRepository.save(status);
    }

    public void deleteStatus(String id){
        statusRepository.deleteById(id);
    }
}
