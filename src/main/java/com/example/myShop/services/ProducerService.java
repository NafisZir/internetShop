package com.example.myShop.services;

import com.example.myShop.models.Producer;
import com.example.myShop.repositories.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProducerService {
    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public List<Producer> getProducerList(){
        return producerRepository.findAll();
    }
}
