package com.example.myShop.services;

import com.example.myShop.models.Receiving;
import com.example.myShop.repositories.ReceivingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivingService {
    private final ReceivingRepository receivingRepository;

    public ReceivingService(ReceivingRepository receivingRepository) {
        this.receivingRepository = receivingRepository;
    }

    public List<Receiving> receivingList(){
        return receivingRepository.findAll();
    }

    public Receiving getReceivingById(Integer id){
        return receivingRepository.findById(id).orElse(null);
    }

    public void saveReceive(Receiving receiving){
        receivingRepository.save(receiving);
    }

    public void deleteReceive(Integer id){
        receivingRepository.deleteById(id);
    }
}
