package com.example.myShop.repositories;

import com.example.myShop.models.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, String> {
}
