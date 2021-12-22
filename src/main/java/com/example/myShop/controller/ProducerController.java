package com.example.myShop.controller;

import com.example.myShop.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nafis
 * @since 20.12.2021
 */

@RestController
@RequestMapping(path = "producers")
@RequiredArgsConstructor
public class ProducerController {
    private final ProducerService producerService;
}
